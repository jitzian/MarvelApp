package com.example.marvelapp.domain.repository.characters

import com.example.marvelapp.data.remote.model.ApiCharacter
import com.example.marvelapp.data.remote.model.ApiResponse

interface CharactersRepository {
    suspend fun fetchCharacters(offset: Int, limit: Int): ApiResponse<ApiCharacter>
}