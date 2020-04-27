
package com.app.fr.leboncoin.di

import com.app.fr.leboncoin.App
import dagger.Component


@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(application: App)
}