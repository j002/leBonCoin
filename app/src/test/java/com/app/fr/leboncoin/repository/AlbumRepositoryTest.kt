package com.app.fr.leboncoin.repository

import android.R
import com.app.fr.leboncoin.models.Album
import com.app.fr.leboncoin.models.AlbumRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doReturn
import org.mockito.Spy
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AlbumRepositoryTest {

    @Mock
    lateinit var albumRepository: AlbumRepository
    @Mock
    lateinit var listAlbum: Single<List<Album>>


    @Before
    @Throws(Exception::class)
    fun setUp() {


    }

    @Test
    fun getAlbums() {
        `when`(albumRepository.getAlbums()).thenReturn(listAlbum)
        println(albumRepository.getAlbums())

    }


}