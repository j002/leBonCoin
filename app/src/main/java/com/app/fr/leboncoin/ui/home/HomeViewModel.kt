package com.app.fr.leboncoin.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.app.fr.leboncoin.album.AlbumDao
import com.app.fr.leboncoin.album.AlbumEntity
import com.app.fr.leboncoin.album.LeBonCoinDatabase
import com.app.fr.leboncoin.core.BaseViewModel
import com.app.fr.leboncoin.models.Album
import com.app.fr.leboncoin.models.AlbumRepositoryImpl
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class HomeViewModel @Inject constructor(val context: Context, private val albumRepositoryImpl: AlbumRepositoryImpl) :
    BaseViewModel() {

    private val livelistAlbum = MutableLiveData<List<Album>>()

    lateinit var albumDao: AlbumDao
    private var listAlbumEntity: LiveData<List<AlbumEntity>>


    init {
        val database: LeBonCoinDatabase? = LeBonCoinDatabase.getInstance(context)
        if (database != null) {
            albumDao = database.albumDao()
        }
        listAlbumEntity = albumDao.getAlbumFromDataBase()
    }

    fun onListAlbumLoaded(): LiveData<List<AlbumEntity>> {
        return listAlbumEntity
    }

    fun onListAlbumLoadedFromServer(): LiveData<List<Album>> {
        return livelistAlbum
    }


    fun getAlbum() {
        albumRepositoryImpl.run {
            getAlbums().subscribeBy(
                onSuccess = {
                    livelistAlbum.value=it
                }
            )
        }

    }




}