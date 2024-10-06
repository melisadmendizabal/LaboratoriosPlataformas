package com.uvg.laboratorio8.presentation.inicio

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object InicioDestination

fun NavGraphBuilder.inicioScreen(
    onEntrarClick: ()-> Unit
){
    composable<InicioDestination>{
        InicioRoute(
            onEntrarClick = onEntrarClick
        )
    }
}