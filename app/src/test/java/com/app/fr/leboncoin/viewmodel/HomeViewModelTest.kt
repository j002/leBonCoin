package com.app.fr.leboncoin.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.fr.leboncoin.models.Album
import com.app.fr.leboncoin.ui.home.HomeViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    lateinit var homeViewModel: HomeViewModel



    @Before
    @Throws(Exception::class)
    fun setUp() {


    }

    @Test
    fun getAlbums() {
        val livelistAlbum = MutableLiveData<List<Album>>()
        var listAlbum: MutableList<Album> = mutableListOf()
        listAlbum.add(Album("Album 1", "Url 1"))
        listAlbum.add(Album("Album 2", "Url 2"))
        listAlbum.add(Album("Album 3", "Url 3"))
        listAlbum.add(Album("Album 4", "Url 4"))
        listAlbum.add(Album("Album 5", "Url 5"))

        livelistAlbum.postValue(listAlbum)


        Mockito.`when`(homeViewModel.onListAlbumLoadedFromServer()).thenReturn(livelistAlbum)
        println(homeViewModel.onListAlbumLoadedFromServer())

    }

}