package com.app.fr.leboncoin.di

import android.app.Activity
import android.content.Context
import com.app.fr.getmymask.helpers.network.NetworkConnectionInterceptor
import com.app.fr.leboncoin.App
import com.app.fr.leboncoin.Constants
import com.app.fr.leboncoin.album.LeBonCoinDatabase
import com.app.fr.leboncoin.api.album.AlbumApiService
import com.app.fr.leboncoin.api.album.AlbumApiServiceImpl
import com.app.fr.leboncoin.models.AlbumRepository
import com.app.fr.leboncoin.models.AlbumRepositoryImpl
import com.app.fr.leboncoin.album.AlbumDao
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.jetbrains.anko.runOnUiThread
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


@Module
class GlobalInjectorModule(var activity: Activity) {

    @Provides
    fun provideContext(): Context = activity

    @Provides
    fun provideApplication(): App = activity.application as App


    //API
    @Provides
    fun provideAlbumApiService(service: AlbumApiServiceImpl): AlbumApiService = service

    //Repos
    @Provides
    fun provideAlbumrRepository(repository: AlbumRepositoryImpl): AlbumRepository = repository


    @Provides
    fun provideRetrofit(): Retrofit {
        val moshi = Moshi.Builder()
            .build()
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(provideOkHttpClient())
            .baseUrl(Constants.DEV_URL)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
         val okhttpClientBuilder = OkHttpClient.Builder()

          okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
          okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
          okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS)
          okhttpClientBuilder.retryOnConnectionFailure(true)
          //todo make better
          okhttpClientBuilder.addInterceptor { chain ->
              val request = chain.request().newBuilder()
                  .header("Content-Type", "application/x-www-form-urlencoded")
                .header("X-Requested-With", "XMLHttpRequest")
                  .build()
              var response = chain.proceed(request)
              var tryCount = 0
              if (response.code() == 401 || response.code() == 403) {
                  provideContext().runOnUiThread {
                      provideApplication().onUnauthorized()
                  }
              } else {
                  while (response.code() == 429 && tryCount < 3) {
                      tryCount++
                      response = chain.proceed(request)
                  }
              }

              response
          }


          okhttpClientBuilder.addInterceptor(object : NetworkConnectionInterceptor() {
              override fun isInternetAvailable(): Boolean {
                  return App.isDeviceConnected
              }

              override fun onInternetUnavailable() {
                  provideContext().runOnUiThread {
                      provideApplication().onInternetUnavailable()
                  }
              }
          })

          return okhttpClientBuilder.build()

    }


}