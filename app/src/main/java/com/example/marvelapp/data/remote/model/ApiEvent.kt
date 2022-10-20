package com.example.marvelapp.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiEvent(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: ApiThumbnail,
    val characters: ApiReferenceList,
    val comics: ApiReferenceList,
    val series: ApiReferenceList,
    val stories: ApiReferenceList,
    val urls: List<ApiUrl>,
    val start: String,
    val end: String,
    val previous: ApiReference,
    val next: ApiReference,
    val modified: String,
    val resourceURI: String
):Parcelable