package com.uvg.laboratorio8.presentation.listado


import androidx.navigation.NavGraphBuilder
import kotlinx.serialization.Serializable
import androidx.navigation.compose.composable


@Serializable
data object ListadoDestination

fun NavGraphBuilder.listadoScreen(
    onCharClick: (Int) -> Unit
){
    composable<ListadoDestination> {
        ListadoRoute(
            onCharClick = onCharClick
        )
    }
}

