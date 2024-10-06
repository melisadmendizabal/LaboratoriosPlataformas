package com.uvg.laboratorio8.presentation.localizacion

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.uvg.laboratorio8.presentation.listadoLocalizacion.ListadoLocalizacionDestination
import com.uvg.laboratorio8.presentation.listadoLocalizacion.listadoLocalizacionScreen
import com.uvg.laboratorio8.presentation.personaje.navigateToPersonajeScreen
import kotlinx.serialization.Serializable

@Serializable
data object LocalizacionesNavGraph

fun NavController.navigateToLocalizacionGraph(
    navOptions: NavOptions? = null
){
    this.navigate(LocalizacionesNavGraph, navOptions)
}


fun NavGraphBuilder.localizacionGraph(
    navController: NavController
) {
    navigation<LocalizacionesNavGraph>(
        startDestination = ListadoLocalizacionDestination
    ) {
        listadoLocalizacionScreen(
            onLocaClick = navController::navigateToLocalizationScreen
        )

        localizationScreen(
            onNavigationLocaBack = navController::navigateUp
        )
    }
}

