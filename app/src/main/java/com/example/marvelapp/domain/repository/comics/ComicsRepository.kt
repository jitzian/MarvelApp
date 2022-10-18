package com.example.marvelapp.domain.repository.comics

import com.example.marvelapp.data.remote.model.ApiComic
import com.example.marvelapp.data.remote.model.ApiResponse

interface ComicsRepository {
    suspend fun fetchComics(characterId: Int): ApiResponse<ApiComic>
}