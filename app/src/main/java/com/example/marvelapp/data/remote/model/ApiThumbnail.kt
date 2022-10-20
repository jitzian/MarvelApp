package com.example.marvelapp.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiThumbnail(
    val extension: String,
    val path: String
) : Parcelable

fun ApiThumbnail.asString() = "$path.$extension".replace("http", "https")