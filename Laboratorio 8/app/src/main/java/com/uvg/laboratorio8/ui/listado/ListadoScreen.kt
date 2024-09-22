package com.uvg.laboratorio8.ui.listado

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.laboratorio8.CharacterDb

@Composable
fun ListadoRoute(
    onCharClick: (Int) -> Unit
){
    val characterDB = CharacterDb()
    ListadoScreen(characterDB, onCharClick = onCharClick)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListadoScreen(characterDb: CharacterDb,
                          onCharClick: (Int) -> Unit){
    Column {
        TopAppBar(
            title = {
                Text(text = "Characters")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            items(characterDb.getAllCharacters()){ character ->
                CartaPersonajes(
                    modifier = Modifier
                        .clickable { onCharClick(character.id) }, //aaaaaaaa
                    id = character.id,
                    nombre = character.name,
                    especie = character.species,
                    status = character.status,
                    image = character.image,
                    onCharClick = {onCharClick(character.id)})//Aaaaaaaaaaaaaaaaaaaaaa
            }
        }
    }
}

@Composable
fun CartaPersonajes(modifier: Modifier,id: Int, nombre: String, especie:String, status:String, image: String, onCharClick: (Int) -> Unit){
    Box(modifier = modifier) {
        Row(){
            Box(modifier = Modifier
                .padding(10.dp)
                .size(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary)
                ){
            }

            Column(verticalArrangement = Arrangement.Center,
                ){
                Text(text=nombre,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Text(text=especie)
                    Text(text=" - ")
                    Text(text=status)
                }

            }


        }

    }
}


