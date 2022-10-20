package com.example.marvelapp.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiUrl(
    val type: String,
    val url: String
) : Parcelable