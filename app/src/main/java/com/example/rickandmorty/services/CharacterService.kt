package com.example.rickandmorty.services

import retrofit2.http.GET
import retrofit2.http.Path
import com.example.rickandmorty.models.Character
import com.example.rickandmorty.models.CharacterResponse
import retrofit2.http.Query

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character
}
