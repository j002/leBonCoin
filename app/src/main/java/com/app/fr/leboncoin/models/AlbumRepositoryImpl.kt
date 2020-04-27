package com.app.fr.leboncoin.models

import android.content.Context
import android.content.Entity
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.app.fr.leboncoin.album.AlbumEntity
import com.app.fr.leboncoin.api.album.AlbumApiServiceImpl
import com.app.fr.leboncoin.api.responsedataapi.ResponseAlbumFromApi
import com.app.fr.leboncoin.album.AlbumDao
import com.app.fr.leboncoin.album.LeBonCoinDatabase
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(private val context: Context) : AlbumRepository {
    @Inject
    lateinit var albumApiServiceImpl: AlbumApiServiceImpl
    private val listAlbum: MutableList<Album> = mutableListOf()
    lateinit var albumDao: AlbumDao


    init {
        val database: LeBonCoinDatabase? = LeBonCoinDatabase.getInstance(context)
        if (database != null) {
            albumDao = database.albumDao()
        }
    }

    override fun getAlbums(): Single<List<Album>> = Single.create { emitter ->

        albumApiServiceImpl.run {
            getAlbums()
                .map { ResponseAlbumFromApi ->
                    ResponseAlbumFromApi.toAlbum()
                }.subscribeBy(
                    onNext = { album ->
                        listAlbum.add(album)

                        insert(AlbumEntity(album.title, album.url))
                    },
                    onComplete = {
                        // albumDao.insertAllAlbums(listAlbumEntity)
                        emitter.onSuccess(listAlbum)

                    },
                    onError = {
                        emitter.onError(it)
                    }
                )
        }

    }

    private fun insert(albumEntity: AlbumEntity) {
        InsertUserAsyncTask(albumDao).execute(albumEntity)
    }


    private class InsertUserAsyncTask(albumDao: AlbumDao) : AsyncTask<AlbumEntity, Unit, Unit>() {
        val albumDao = albumDao
        override fun doInBackground(vararg p0: AlbumEntity?) {
            albumDao.insert(p0[0]!!)
        }
    }



    private fun ResponseAlbumFromApi.toAlbum(): Album {
        return Album(
            this.title!!,
            this.url!!
        )
    }
}