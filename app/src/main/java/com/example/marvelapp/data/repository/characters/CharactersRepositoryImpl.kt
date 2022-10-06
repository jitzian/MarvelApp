package com.example.marvelapp.data.repository.characters

import com.example.marvelapp.data.remote.RestApi
import com.example.marvelapp.data.remote.model.ApiCharacter
import com.example.marvelapp.data.remote.model.ApiResponse
import com.example.marvelapp.domain.repository.characters.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val restApi: RestApi
) : CharactersRepository {
    override suspend fun fetchCharacters(offset: Int, limit: Int): ApiResponse<ApiCharacter> {
        return restApi.fetchCharacters(offset, limit)
    }
}