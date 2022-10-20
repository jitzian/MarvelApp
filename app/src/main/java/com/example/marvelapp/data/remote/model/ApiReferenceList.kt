package com.example.marvelapp.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiReferenceList(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiReference>?,
    val returned: Int
) : Parcelable