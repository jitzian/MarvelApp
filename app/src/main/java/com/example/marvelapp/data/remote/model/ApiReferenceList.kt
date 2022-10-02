package com.example.marvelapp.data.remote.model

import com.example.marvelapp.data.remote.model.ApiReference

data class ApiReferenceList(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiReference>?,
    val returned: Int
)