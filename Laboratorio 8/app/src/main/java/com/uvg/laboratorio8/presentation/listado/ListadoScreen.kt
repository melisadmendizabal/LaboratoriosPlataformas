package com.uvg.laboratorio8.presentation.listado

import androidx.compose.runtime.getValue
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uvg.laboratorio8.data.PantallaCarga
import com.uvg.laboratorio8.data.PantallaError
import com.uvg.laboratorio8.data.model.Character


@Composable
fun ListadoRoute(
    onCharClick: (Int) -> Unit,
    viewModel: ListadoViewModel = viewModel()
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.obtenerListadoPersonaje()
    }

    ListadoScreen(
        state = state,
        listado = state.data,
        onCharClick = onCharClick,
        isLoading = state.loading,
        hasError = state.hasError,
        onError = {viewModel.onError()},
        onReintentar = {
            viewModel.reiniciarEstado()
            viewModel.obtenerListadoPersonaje()
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListadoScreen(
    state: ListadoState,
    listado: List<Character>?,
    onCharClick: (Int) -> Unit,
    isLoading: Boolean,
    hasError: Boolean,
    onError: () ->Unit,
    onReintentar: () ->Unit
) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        TopAppBar(
            title = { Text(text = "Characters") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        Box{
            when{
                isLoading -> {
                    PantallaCarga(onCargando = onError)
                }

                hasError -> {
                    PantallaError(nombrePantalla = "el listado de personajes",
                        onReintentar = onReintentar
                    )
                }

                listado == null -> {
                    Text(text = "No se encontraron los datos del listado de personajes")
                }

                else -> {
                    PantallaListado(personajes = listado,
                        onCharClick = onCharClick
                    )
                }
            }
        }
    }



}

@Composable
fun PantallaListado(
                    personajes: List<Character>,
                    onCharClick: (Int) -> Unit){
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier.padding(10.dp)
        ) {

            items(personajes) { character ->
                CartaPersonajes(
                    modifier = Modifier
                        .clickable { onCharClick(character.id) }, //aaaaaaaa
                    nombre = character.name,
                    especie = character.species,
                    status = character.status
                )
            }
        }
    }
}

@Composable
fun CartaPersonajes(modifier: Modifier, nombre: String, especie:String, status:String){
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

