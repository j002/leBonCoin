package com.app.fr.leboncoin.api.album

import android.content.Context
import com.app.fr.leboncoin.api.ApiClientImpl
import com.app.fr.leboncoin.api.responsedataapi.ResponseAlbumFromApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class AlbumApiServiceImpl @Inject constructor(val context: Context, retrofit: Retrofit) : AlbumApiService, ApiClientImpl() {

    private var userApi: AlbumApi = retrofit.create(AlbumApi::class.java)


    override fun getAlbums(): Observable<ResponseAlbumFromApi> = Observable.create { emitter ->
        userApi.getAlbums()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { response ->
                    if (response.isSuccessful) {
                        response.body()!!.forEach { user ->
                            emitter.onNext(user)
                        }
                        emitter.onComplete()
                    } else {
                        handleStatusCodeToSendEmitter(emitter, response.code())
                    }
                },
                onError = {
                    emitter.onError(it)
                })
    }
}