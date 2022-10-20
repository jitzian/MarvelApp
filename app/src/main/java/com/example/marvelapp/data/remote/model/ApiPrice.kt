package com.example.marvelapp.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiPrice(
    val price: Double,
    val type: String
):Parcelable