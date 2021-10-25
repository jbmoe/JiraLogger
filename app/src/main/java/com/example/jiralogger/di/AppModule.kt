package com.example.jiralogger.di

import android.app.Application
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import coil.ImageLoader
import coil.util.CoilUtils
import com.example.jiralogger.data.remote.JiraApi
import com.example.jiralogger.domain.repository.JiraRepository
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.data.LogDatabase
import com.example.jiralogger.data.repository.JiraRepositoryImpl
import com.example.jiralogger.data.repository.JiraRepositoryTestImpl
import com.example.jiralogger.data.repository.LogRepositoryImpl
import com.example.jiralogger.domain.repository.LogRepository
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
import kotlin.coroutines.coroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val _client = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor(Constants.USERNAME, Constants.PASSWORD))
        .build()
    val client: OkHttpClient = _client

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
//        return JiraRepositoryTestImpl()
        return JiraRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideLogDatabase(app: Application): LogDatabase {
        return Room.databaseBuilder(
            app,
            LogDatabase::class.java,
            LogDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLogRepository(db: LogDatabase): LogRepository {
        return LogRepositoryImpl(db.logDao)
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