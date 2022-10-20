package com.example.marvelapp.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiReference(
    val name: String,
    val resourceURI: String
) : Parcelable