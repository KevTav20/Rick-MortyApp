package com.example.rickandmorty.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rickandmorty.R
import com.example.rickandmorty.models.Character
import com.example.rickandmorty.models.Location
import com.example.rickandmorty.models.Origin
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import androidx.compose.material3.CardDefaults

@Composable
fun CharacterCard(character: Character, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable { onClick() }
            .shadow(0.dp, RoundedCornerShape(0.dp)),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF3E3E3E))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color(0xFF212121))
                .padding(15.dp)
                .height(240.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(R.drawable.rick)
                    .data(character.image)
                    .build(),
                contentDescription = character.name,
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD1D1D1))
                    .padding(10.dp)
                    .shadow(8.dp, CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                text = character.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF00FF00),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            // Añadir estado y especie
            Text(
                text = "${character.status} - ${character.species}",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterCard() {

    val exampleCharacter = Character(
        created = "2017-11-04T18:48:46.250Z",
        episode = listOf("S01E01", "S01E02"),
        gender = "Male",
        id = 1,
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        location = Location(name = "Earth", url = "https://rickandmortyapi.com/api/location/1"),
        name = "Rick Sanchez",
        origin = Origin(name = "Earth", url = "https://rickandmortyapi.com/api/location/1"),
        species = "Human",
        status = "Alive",
        type = "Human",
        url = "https://rickandmortyapi.com/api/character/1"
    )

    RickAndMortyTheme {
        CharacterCard(character = exampleCharacter){}
    }
}
