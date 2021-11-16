package com.example.jiralogger

import android.app.Application
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import coil.util.CoilUtils
import com.example.jiralogger.di.AppModule
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient

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
            .okHttpClient(AppModule.client)
            .build()
    }

    companion object {
        lateinit var instance: JiraLoggerApplication
            private set
    }
}