package com.app.fr.leboncoin.ui.home.di

import com.app.fr.leboncoin.di.GlobalInjectorModule
import com.app.fr.leboncoin.di.ViewModelModule
import com.app.fr.leboncoin.ui.home.HomeActivity
import dagger.Component

@Component(modules = [GlobalInjectorModule::class, ViewModelModule::class])
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)

}
