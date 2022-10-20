package com.example.marvelapp.data.remote

import com.example.marvelapp.data.remote.model.ApiCharacter
import com.example.marvelapp.data.remote.model.ApiComic
import com.example.marvelapp.data.remote.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET("/v1/public/characters")
    suspend fun fetchCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<ApiCharacter>

    @GET("/v1/public/characters/{characterId}")
    suspend fun fetchComics(@Path("characterId") characterId: Int): ApiResponse<ApiComic>

    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("format") format: String?
    ): ApiResponse<ApiComic>

    @GET("/v1/public/comics/{comicId}")
    suspend fun findComic(
        @Path("comicId") comicId: Int,
    ): ApiResponse<ApiComic>

    //TODO: Add EndPoint...
    suspend fun fetchEvents()

}