package com.example.cameraalbum.repository

import androidx.lifecycle.LiveData
import com.example.cameraalbum.model.Photo
import com.example.kotlinroomdatabase.data.PhotoDao

class PhotoRepository(private val photoDao: PhotoDao) {
    val readAllData: LiveData<List<Photo>> = photoDao.readAllData()

    suspend fun addPhoto(photoModel: Photo) {
        photoDao.addPhoto(photoModel)
    }

}