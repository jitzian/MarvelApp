package com.example.marvelapp.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ApiData<T>(
    val count: Int,
    val limit: Int,
    val offset: Int,
    //val results: List<T>,
    val results: @RawValue List<T>,
    val total: Int
) : Parcelable