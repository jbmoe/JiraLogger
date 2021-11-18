package com.example.jiralogger

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.example.jiralogger.common.constant.Constants
import com.example.jiralogger.di.AppModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JiraLoggerApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun newImageLoader(): ImageLoader {
        val context = applicationContext
        return ImageLoader.Builder(context)
            .crossfade(true)
            .componentRegistry {
                add(SvgDecoder(context))
            }
            .okHttpClient(AppModule.client(Constants.USERNAME, Constants.PASSWORD))
            .build()
    }

    companion object {
        lateinit var instance: JiraLoggerApplication
            private set
    }
}