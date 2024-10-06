package com.uvg.laboratorio8.presentation.personaje

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uvg.laboratorio8.data.PantallaCarga
import com.uvg.laboratorio8.data.PantallaError
import com.uvg.laboratorio8.data.model.Character


@Composable
fun PersonajeRoute(
    onNavigateBack: () -> Unit,
    viewModel: PersonajeViewModel = viewModel()
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.obtenerDatosPersonajes()
    }

    PersonajeScreen(
        state = state,
        onNavigationBack = onNavigateBack,
        onError = {
            viewModel.onError()
        },
        onReintentar = {
            viewModel.reiniciarEstado()
            viewModel.obtenerDatosPersonajes()
        }
    )



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersonajeScreen(
    state: PersonajeState,
    onNavigationBack: () -> Unit,
    onError: () -> Unit,
    onReintentar: () -> Unit
){
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

        MostrarEstadosPersonajes(
            personaje = state.data,
            isLoading = state.loading,
            hasError = state.hasError,
            onError = onError,
            onReintentar = onReintentar
        )
    }
}

@Composable
private fun MostrarEstadosPersonajes(
    personaje: Character?,
    isLoading: Boolean,
    hasError: Boolean,
    onError: () -> Unit,
    onReintentar: () -> Unit
){
    Box {
        when{
            isLoading -> {
                PantallaCarga(onCargando = onError)
            }

            hasError -> {
                PantallaError(nombrePantalla = "los datos del personaje",
                    onReintentar = onReintentar)
            }

            personaje == null -> {
                Text(text = "No se encontraron los datos del personaje")
            }

            else -> {
                ContenidoPantalla(
                    name = personaje.name,
                    species = personaje.species,
                    status = personaje.status,
                    gender = personaje.gender)
            }

        }
    }


}

@Composable
fun ContenidoPantalla(name: String, species: String, status:String, gender: String ){

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 10.dp)) {

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
            Column {
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
