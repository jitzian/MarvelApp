package com.example.marvelapp.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiComic(
    val characters: ApiReferenceList,
    val creators: ApiReferenceList,
    val description: String?,
    val diamondCode: String,
    val digitalId: Int,
    val ean: String,
    val events: ApiReferenceList,
    val format: String,
    val id: Int,
    val isbn: String,
    val issn: String,
    val issueNumber: Int,
    val modified: String,
    val pageCount: Int,
    val prices: List<ApiPrice>,
    val resourceURI: String,
    val series: ApiReferenceList,
    val stories: ApiReferenceList,
    val thumbnail: ApiThumbnail,
    val title: String,
    val upc: String,
    val urls: List<ApiUrl>,
    val variantDescription: String,
    val variants: List<ApiVariant>
): Parcelable