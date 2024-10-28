package com.example.rickandmorty.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rickandmorty.services.CharacterService
import com.example.rickandmorty.ui.components.CharacterDetailCard
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.rickandmorty.models.Character
import LoadingScreen

@Composable
fun CharacterDetailScreen(id: Int, innerPadding: PaddingValues) {
    val scope = rememberCoroutineScope()
    var character by remember { mutableStateOf<Character?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(id) {
        scope.launch {
            try {
                val BASE_URL = "https://rickandmortyapi.com/api/"
                val characterService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CharacterService::class.java)

                // Obtener el personaje por ID
                character = characterService.getCharacterById(id)
            } catch (e: Exception) {
                Log.e("API Error", e.toString())
                character = null
            } finally {
                isLoading = false
            }
        }
    }

    if (isLoading) {
        LoadingScreen()
    } else {
        if (character != null) {
            CharacterDetailCard(character!!)
        } else {
            Text(text = "Character not found", modifier = Modifier.padding(innerPadding))
        }
    }
}

