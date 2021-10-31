package com.example.jiralogger.di

import android.app.Application
import androidx.room.Room
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.common.test_data.TestDB
import com.example.jiralogger.data.local.JiraLoggerDatabase
import com.example.jiralogger.data.remote.JiraApi
import com.example.jiralogger.data.repository.ApiRepositoryImpl
import com.example.jiralogger.data.repository.DbRepositoryImpl
import com.example.jiralogger.data.repository.DbRepositoryTestImpl
import com.example.jiralogger.domain.repository.ApiRepository
import com.example.jiralogger.domain.repository.DbRepository
import com.example.jiralogger.domain.util.BasicAuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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
    fun provideIssueRepository(api: JiraApi): ApiRepository {
//        return ApiRepositoryTestImpl()
        return ApiRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideLogDatabase(app: Application): JiraLoggerDatabase {
        return Room.databaseBuilder(
            app,
            JiraLoggerDatabase::class.java,
            JiraLoggerDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLogRepository(db: JiraLoggerDatabase): DbRepository {
//        return DbRepositoryTestImpl()
        return DbRepositoryImpl(db.jiraLoggerDao)
    }
}