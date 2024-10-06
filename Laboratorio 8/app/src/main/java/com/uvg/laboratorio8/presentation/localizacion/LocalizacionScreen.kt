package com.uvg.laboratorio8.presentation.localizacion


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uvg.laboratorio8.data.PantallaCarga
import com.uvg.laboratorio8.data.PantallaError
import com.uvg.laboratorio8.data.model.Location
import com.uvg.laboratorio8.presentation.ui.theme.Laboratorio8Theme


@Composable
fun LocalizacionRoute(
    onNavigationLocaBack: () -> Unit,
    viewModel: LocalizacionViewModel = viewModel()
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.obtenerDatosLocalizacion()
    }

    LocalizacionScreen(
        state = state,
        onNavigationLocaBack = onNavigationLocaBack,
        onError = {
            viewModel.onError()
        },
        onReintentar = {
            viewModel.reiniciarEstado()
            viewModel.obtenerDatosLocalizacion()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocalizacionScreen(
    state: LocalizacionState,
    onNavigationLocaBack: () -> Unit,
    onError: () -> Unit,
    onReintentar: () -> Unit
){
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        
        TopAppBar(
            title = { Text(text = "Location Details")},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            navigationIcon = {
                IconButton(onClick = onNavigationLocaBack) {
                    Icon(
                        Icons.Filled.Clear, contentDescription = "back"
                    )
                    
                }
            }
        )
        MostrarPantallaLocalizacion(
            location = state.data,
            isLoading = state.loading,
            hasError = state.hasError,
            onError = onError,
            onReintentar = onReintentar
            
        )
    }
}

@Composable
private fun MostrarPantallaLocalizacion(
    location: Location?,
    isLoading: Boolean,
    hasError: Boolean,
    onError: () ->Unit,
    onReintentar: () ->Unit
    ){
    Box {
        when{
            isLoading -> {
                PantallaCarga(onCargando = onError)
            }

            hasError -> {
                PantallaError(nombrePantalla = "los datos de la localizacion",
                    onReintentar = onReintentar)

            }


            location == null -> {
                Text(text = "No se encontraron datos de localización.")
            }

            else -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(text = location.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Row(modifier = Modifier
                        .padding(40.dp)){
                        Column(modifier = Modifier.weight(0.5f)) {
                            Text(text = "ID:")
                            Text(text = "Type:")
                            Text(text = "Dimensions:")

                        }
                        Column {
                            Text(text = location.id.toString(),
                                textAlign = TextAlign.End)
                            Text(text = location.type,
                                textAlign = TextAlign.End)
                            Text(text = location.dimension,
                                textAlign = TextAlign.End)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewEmptyLocationProfileScreen() {
    Laboratorio8Theme {
        Surface {
            LocalizacionScreen(
                state = LocalizacionState(
                    loading = false,
                    data = Location(
                        1,
                        "jajajs",
                        "jajdajsa",
                        "yeah"
                    )
                ),
                onNavigationLocaBack = { },
                onError = {},
                onReintentar = {}
            )
        }
    }
}