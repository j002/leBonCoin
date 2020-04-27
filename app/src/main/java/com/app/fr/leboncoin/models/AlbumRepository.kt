package com.app.fr.leboncoin.models

import io.reactivex.Single

interface AlbumRepository {
    fun getAlbums(): Single<List<Album>>
}