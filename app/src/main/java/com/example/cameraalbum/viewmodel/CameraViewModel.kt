package com.example.cameraalbum.viewmodel

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.*
import com.example.cameraalbum.model.Photo
import com.example.cameraalbum.repository.PhotoRepository
import com.example.kotlinroomdatabase.data.PhotoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CameraViewModel(application: Application): AndroidViewModel(application) {

    private val repository_new: PhotoRepository
    lateinit var photoentity: Photo
    val album_Name="album${System.currentTimeMillis()}"
    var timer: CountDownTimer
    var isTimerRunning =false;


    init {
        val photoDao = PhotoDatabase.getDatabase(application).photoDao()
        repository_new = PhotoRepository(photoDao)
        // time count down for 5 seconds,
        // with 1 second as countDown interval
        timer = object: CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                isTimerRunning = true
            }

            override fun onFinish() {
                isTimerRunning = false
                photo = photo?.replaceFirst("null, ","")
                Log.e("photoString","list is ${photo}")
                photoentity = Photo(0, album_Name,System.currentTimeMillis().toString(),photo!!)
                addPhototoDb(photoentity)
            }
        }

    }

    var photo:String? = null
    fun addPhoto(photopath:String)
    {
        if(!isTimerRunning){
            timer.start()
        }
        photo = "${photo}, ${photopath}"
    }


    fun addPhototoDb(photoModel: Photo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository_new.addPhoto(photoModel)
        }
    }



}