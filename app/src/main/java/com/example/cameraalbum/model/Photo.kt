package com.example.cameraalbum.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "photo_table")
data class Photo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val albumName: String,
    val timeStamp: String,
    val photoName: String
): Parcelable