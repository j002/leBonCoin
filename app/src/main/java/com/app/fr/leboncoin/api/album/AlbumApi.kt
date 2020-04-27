package com.app.fr.leboncoin.api.album

import com.app.fr.leboncoin.api.responsedataapi.ResponseAlbumFromApi
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface AlbumApi {

    @Headers("X-Requested-With: XMLHttpRequest")
    @GET("img/shared/technical-test.json")
    fun getAlbums(): Single<Response<List<ResponseAlbumFromApi>>>
}