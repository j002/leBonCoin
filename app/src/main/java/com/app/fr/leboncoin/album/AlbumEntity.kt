package com.app.fr.leboncoin.album

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class AlbumEntity(val title: String, val url: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}