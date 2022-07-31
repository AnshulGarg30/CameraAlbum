package com.example.cameraalbum.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.cameraalbum.model.Photo
import com.example.cameraalbum.repository.PhotoRepository
import com.example.kotlinroomdatabase.data.PhotoDatabase

class GallaryViewmodel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Photo>>
    private val repository_new: PhotoRepository

    init {
        val photoDao = PhotoDatabase.getDatabase(application).photoDao()
        repository_new = PhotoRepository(photoDao)
        readAllData = repository_new.readAllData
    }

}