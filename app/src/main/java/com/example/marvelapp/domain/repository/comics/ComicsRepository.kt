package com.example.marvelapp.domain.repository.comics

import com.example.marvelapp.data.remote.model.ApiCharacter
import com.example.marvelapp.data.remote.model.ApiComic
import com.example.marvelapp.data.remote.model.ApiResponse
import retrofit2.http.Query

interface ComicsRepository {
    suspend fun fetchCharacters(offset: Int, limit: Int): ApiResponse<ApiCharacter>
    suspend fun fetchComics(characterId: Int): ApiResponse<ApiComic>
    suspend fun getComics(offset: Int, limit: Int, format: String?): ApiResponse<ApiComic>
}