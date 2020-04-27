package com.app.fr.leboncoin.album

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.fr.leboncoin.album.AlbumEntity
import io.reactivex.Single


@Dao
interface AlbumDao {
    @Insert
    fun insert(albumEntity: AlbumEntity)

    @Query("SELECT * FROM album_table")
    fun getAlbumFromDataBase(): LiveData<List<AlbumEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlbums(albumEntitys : List<AlbumEntity>)
}