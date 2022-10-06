package com.example.marvelapp.domain.repository.comics

import com.example.marvelapp.data.remote.model.ApiCharacter
import com.example.marvelapp.data.remote.model.ApiResponse

interface ComicsRepository {
    suspend fun fetchComics(characterId: Int): ApiResponse<ApiCharacter>
}