package com.uvg.laboratorio8.presentation.listadoLocalizacion

import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uvg.laboratorio8.data.PantallaCarga
import com.uvg.laboratorio8.data.PantallaError
import com.uvg.laboratorio8.data.model.Location
import com.uvg.laboratorio8.data.sourse.LocationDb
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ListadoLocalizacionRoute(
    onLocaClick: (Int) -> Unit,
    viewModel: ListadoLocalizacionViewModel = viewModel()
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.obtenerListadolocalizacione()
    }

    ListadoLocalizacionScreen(
        state = state,
        onLocaClick = onLocaClick,
        onError = {
            viewModel.onError()
        },
        onReintentar = {
            viewModel.reiniciarEstado()
            viewModel.obtenerListadolocalizacione()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoLocalizacionScreen(
    state: ListadoLocalizacionState,
    onLocaClick: (Int) -> Unit,
    onError: () -> Unit,
    onReintentar: () -> Unit
){
    Column{
        TopAppBar(
            title = { Text(text = "Locations") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        MostrarPantallalistadoLoca(
            localizaciones = state.data,
            isLoading = state.loading,
            hasError = state.hasError,
            onError = onError,
            onReintentar = onReintentar,
            onLocaClick = onLocaClick
        )
    }
}

@Composable
private fun MostrarPantallalistadoLoca(
    localizaciones: List<Location>?,
    isLoading: Boolean,
    hasError: Boolean,
    onError: () -> Unit,
    onReintentar: () -> Unit,
    onLocaClick: (Int) -> Unit
){
    Box{
        when{
            isLoading -> {
                PantallaCarga(onCargando =  onError)
            }

            hasError -> {
                PantallaError(nombrePantalla = "el listado de localizaciones",
                    onReintentar =onReintentar)
            }

            localizaciones == null -> {
                Text(text = "No se encontraron los datos del listado de localizaciones")
            }

            else -> {
                PantallaListadoLocalizacion(
                    localizaciones = localizaciones,
                    onLocaClick = onLocaClick
                )
            }
        }
    }

}

@Composable
fun PantallaListadoLocalizacion(
    localizaciones: List<Location>,
    onLocaClick: (Int) -> Unit
){

    Box(modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier.padding(20.dp)

        ) {
            items(localizaciones){ locations ->
                ItemLocation(
                    modifier = Modifier
                        .clickable { onLocaClick(locations.id) },
                    nombre = locations.name,
                    tipo = locations.type
                )

            }

        }
    }

}

@Composable
fun ItemLocation(modifier: Modifier, nombre: String, tipo: String){
    Box(modifier = modifier.padding(vertical = 10.dp)){
        Column {
            Text(text = nombre,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
            Text(text = tipo)
        }
    }
}
