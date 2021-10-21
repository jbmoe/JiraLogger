package com.example.jiralogger.di

import com.example.jiralogger.data.remote.JiraApi
import com.example.jiralogger.data.repository.JiraRepositoryImpl
import com.example.jiralogger.domain.repository.JiraRepository
import com.example.jiralogger.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val client = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor(Constants.USERNAME, Constants.PASSWORD))
        .build()

    @Provides
    @Singleton
    fun provideJiraApi(): JiraApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JiraApi::class.java)
    }

    @Provides
    @Singleton
    fun provideIssueRepository(api: JiraApi): JiraRepository {
//        return TestImpl()
        return JiraRepositoryImpl(api)
    }
}

class BasicAuthInterceptor(username: String, password: String) : Interceptor {
    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()
        return chain.proceed(request)
    }
}