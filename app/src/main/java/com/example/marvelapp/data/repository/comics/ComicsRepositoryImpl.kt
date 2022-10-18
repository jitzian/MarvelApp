package com.example.marvelapp.data.repository.comics

import com.example.marvelapp.data.remote.RestApi
import com.example.marvelapp.data.remote.model.ApiCharacter
import com.example.marvelapp.data.remote.model.ApiComic
import com.example.marvelapp.data.remote.model.ApiResponse
import com.example.marvelapp.domain.repository.comics.ComicsRepository
import javax.inject.Inject

class ComicsRepositoryImpl @Inject constructor(
    private val restApi: RestApi
) : ComicsRepository {
    override suspend fun fetchComics(characterId: Int): ApiResponse<ApiComic> {
        return restApi.fetchComics(characterId)
    }
}