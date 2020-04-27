package com.app.fr.leboncoin.api.album

import com.app.fr.leboncoin.api.responsedataapi.ResponseAlbumFromApi
import io.reactivex.Observable

interface AlbumApiService {
    fun getAlbums(): Observable<ResponseAlbumFromApi>
}