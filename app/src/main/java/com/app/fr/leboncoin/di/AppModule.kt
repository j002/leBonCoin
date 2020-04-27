
package com.app.fr.leboncoin.di

import android.content.Context
import com.app.fr.leboncoin.AppLifecycleObserver
import com.app.fr.leboncoin.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: App) {

    @Provides
    fun provideApplicationContext(): Context = application

    @Provides
    fun provideAppLifecycleObserver(): AppLifecycleObserver = AppLifecycleObserver(application)

}