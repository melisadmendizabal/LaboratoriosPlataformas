package com.uvg.laboratorio8.presentation.listadoLocalizacion

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.uvg.laboratorio8.presentation.localizacion.localizationScreen
import com.uvg.laboratorio8.presentation.localizacion.navigateToLocalizationScreen
import kotlinx.serialization.Serializable

@Serializable
data object  ListadoLocalizacionDestination

fun NavGraphBuilder.listadoLocalizacionScreen(
    onLocaClick: (Int) -> Unit
){
    composable<ListadoLocalizacionDestination>{
        ListadoLocalizacionRoute(
            onLocaClick = onLocaClick
        )
    }
}