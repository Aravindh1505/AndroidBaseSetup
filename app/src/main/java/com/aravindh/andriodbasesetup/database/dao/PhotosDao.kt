package com.aravindh.andriodbasesetup.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aravindh.andriodbasesetup.database.entities.Photos

@Dao
interface PhotosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insert(photos: List<Photos>)

    @Query("SELECT * FROM photos_table")
    fun getPhotos(): LiveData<List<Photos>>

    @Query("SELECT COUNT(*) FROM photos_table")
    fun getCount(): Int
}