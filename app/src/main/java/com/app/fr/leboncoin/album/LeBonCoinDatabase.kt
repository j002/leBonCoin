package com.app.fr.leboncoin.album

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AlbumEntity::class], version = 1)
abstract class LeBonCoinDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao

    companion object {
        @Volatile
        private var instance: LeBonCoinDatabase? = null
        fun getInstance( context: Context): LeBonCoinDatabase? {
            if (instance == null) {
                synchronized(LeBonCoinDatabase::class.java) {
                    instance = Room.databaseBuilder(context.applicationContext, LeBonCoinDatabase::class.java, "Album_DataBase")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }
}