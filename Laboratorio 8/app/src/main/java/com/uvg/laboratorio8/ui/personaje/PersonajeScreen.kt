package com.uvg.laboratorio8.ui.personaje

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.laboratorio8.CharacterDb
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme

@Composable
fun PersonajeRoute(
    id: Int,
    onNavigateBack: () -> Unit
){
    val MyBD = CharacterDb()
    val IDPersonaje = MyBD.getCharacterById(id)

    PersonajeScreen(
        id = id,
        name = IDPersonaje.name,
        status = IDPersonaje.status,
        species = IDPersonaje.species,
        gender = IDPersonaje.gender,
        image = IDPersonaje.image,
        onNavigationBack = onNavigateBack
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersonajeScreen(id: Int, name: String, status:String,species: String, gender:String,
                            image: String, onNavigationBack: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){


        TopAppBar(
            title = { Text(text = "Character Detail") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            navigationIcon = {
                IconButton(onClick = onNavigationBack) {
                    Icon(
                        Icons.Filled.Clear, contentDescription = "back")
                }
            }

        )

        Box(modifier = Modifier
            .padding(vertical = 15.dp)
            .size(200.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondary)

        )    {

        }
        Text(text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold)

        Row(modifier = Modifier
            .padding(40.dp)){
            Column(modifier = Modifier.weight(0.5f)) {
                Text(text = "Species: ")
                Text(text = "Status: ")
                Text(text = "Gender: ")

            }
            Column() {
                Text(text = species,
                    textAlign = TextAlign.End)
                Text(text = status,
                    textAlign = TextAlign.End)
                Text(text = gender,
                    textAlign = TextAlign.End)
            }
        }


    }
}

