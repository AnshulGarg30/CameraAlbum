package com.example.kotlinroomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cameraalbum.model.Photo

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPhoto(photoModel: Photo)

    @Query("SELECT * from photo_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Photo>>
}