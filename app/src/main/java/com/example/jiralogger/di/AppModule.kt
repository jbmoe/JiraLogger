package com.example.jiralogger.di

import android.app.Application
import androidx.room.Room
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.data.local.JiraLoggerDatabase
import com.example.jiralogger.data.remote.JiraApi
import com.example.jiralogger.data.repository.ApiRepositoryImpl
import com.example.jiralogger.data.repository.DbRepositoryImpl
import com.example.jiralogger.domain.repository.ApiRepository
import com.example.jiralogger.domain.repository.DbRepository
import com.example.jiralogger.domain.use_case.user_credential.DeleteUserCredential
import com.example.jiralogger.domain.use_case.user_credential.GetUserCredential
import com.example.jiralogger.domain.use_case.user_credential.InsertUserCredential
import com.example.jiralogger.domain.use_case.user_credential.UserCredentialUseCases
import com.example.jiralogger.domain.use_case.work_log.*
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

    private val _client: (username: String, password: String) -> OkHttpClient =
        { username, password ->
            OkHttpClient.Builder()
                .addInterceptor(BasicAuthInterceptor(username, password))
                .build()
        }
    val client: (username: String, password: String) -> OkHttpClient =
        { username, password -> _client(username, password) }

    @Provides
    @Singleton
    fun provideWorkLogUseCases(repository: DbRepository): WorkLogUseCases {
        return WorkLogUseCases(
            getWorkLog = GetWorkLog(repository),
            getWorkLogs = GetWorkLogs(repository),
            insertWorkLog = InsertWorkLog(repository),
            deleteWorkLog = DeleteWorkLog(repository)
        )
    }

    @Provides
    @Singleton
    fun providesUserCredentialUseCases(repository: DbRepository): UserCredentialUseCases {
        return UserCredentialUseCases(
            getUserCredential = GetUserCredential(repository),
            insertUserCredential = InsertUserCredential(repository),
            deleteUserCredential = DeleteUserCredential(repository)
        )
    }

    @Provides
    @Singleton
    fun provideJiraApi(UsernamePassword: UsernamePassword): JiraApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client(UsernamePassword.username, UsernamePassword.password))
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
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideLogRepository(db: JiraLoggerDatabase): DbRepository {
//        return DbRepositoryTestImpl()
        return DbRepositoryImpl(db.jiraLoggerDao)
    }

    @Provides
    @Singleton
    fun provideUsernamePassword(): UsernamePassword {
        return UsernamePassword(Constants.USERNAME, Constants.PASSWORD)
    }
}

data class UsernamePassword(val username: String, val password: String)