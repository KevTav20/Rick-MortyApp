package com.example.rickandmorty.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
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

@Composable
fun CharacterDetailCard(character: Character) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF1B1F32), Color(0xFF283046))
                )
            )
            .padding(horizontal = 20.dp)
            .padding(top = 50.dp)
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .shadow(8.dp, MaterialTheme.shapes.medium)
                .padding(0.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.elevatedCardElevation(20.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = Color.Transparent)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Imagen del personaje
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .placeholder(R.drawable.rick)
                        .data(character.image)
                        .build(),
                    contentDescription = character.name,
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp)
                        .shadow(8.dp, CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Nombre del personaje
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color(0xFFB5EAEA),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Estado del personaje
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                ) {
                    Text(
                        text = "State: ",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Color(0xFFB5EAEA),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier

                    )
                    Text(
                        text = character.status,
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Color(0xFFB5EAEA),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        imageVector = if (character.status == "Alive") Icons.Default.CheckCircle else Icons.Default.Clear,
                        contentDescription = if (character.status == "Alive") "Alive" else "Deceased",
                        tint = if (character.status == "Alive") Color.Green else Color.Red,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Especie del personaje
                Text(
                    text = "Species: ${character.species}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color(0xFFBBE1FA),
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Información de ubicación
                Text(
                    text = "Location: ${character.location.name}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .padding(horizontal = 50.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))

                // Información de género
                Text(
                    text = "Gender: ${character.gender}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .padding(horizontal = 50.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))

                // Información de origen
                Text(
                    text = "Origin: ${character.origin.name}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .padding(horizontal = 50.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Lista de episodios con dropdown
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF3A6073), Color(0xFF1B1F32))
                            )
                        )
                        .padding(vertical = 20.dp)
                        .padding(horizontal = 40.dp)
                ) {
                    Text(
                        text = "Episodes:",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Color(0xFFE6E6E6),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = { isExpanded = !isExpanded }) {
                        Text(
                            text = if (isExpanded) "Hide Episodes" else "Show Episodes",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

                    if (isExpanded) {
                        // Usar LazyColumn para la lista de episodios
                        LazyColumn {
                            items(character.episode) { episode ->
                                Text(
                                    text = episode, // Aquí se puede cambiar a nombre del episodio
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.LightGray,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterDetailCard() {
    val exampleCharacter = Character(
        created = "2017-11-04T18:48:46.250Z",
        episode = listOf("Rick's Sayonara", "Rick Potion No. 9"), // Nombres de los episodios
        gender = "Male",
        id = 1,
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        location = Location(name = "Earth", url = "https://rickandmortyapi.com/api/location/1"),
        name = "Abadango Cluster Princess",
        origin = Origin(name = "Earth", url = "https://rickandmortyapi.com/api/location/1"),
        species = "Human",
        status = "Alive",
        type = "Human",
        url = "https://rickandmortyapi.com/api/character/1"
    )

    RickAndMortyTheme {
        CharacterDetailCard(character = exampleCharacter)
    }
}
