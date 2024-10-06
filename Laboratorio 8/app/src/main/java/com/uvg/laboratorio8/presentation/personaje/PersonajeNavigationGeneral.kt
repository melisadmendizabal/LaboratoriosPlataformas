package com.uvg.laboratorio8.presentation.personaje

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.uvg.laboratorio8.presentation.listado.ListadoDestination
import com.uvg.laboratorio8.presentation.listado.listadoScreen
import kotlinx.serialization.Serializable

@Serializable
data object PersonajesNavGraph

fun NavGraphBuilder.personajesGraph(
    navController: NavController
){
    navigation<PersonajesNavGraph>(
        startDestination = ListadoDestination
    ){
        listadoScreen(
            onCharClick = navController::navigateToPersonajeScreen
        )

        personajeScreen(
            onNavigateBack = navController::navigateUp
        )

    }


}
