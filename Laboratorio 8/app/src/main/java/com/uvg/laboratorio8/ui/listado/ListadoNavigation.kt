package com.uvg.laboratorio8.ui.listado

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable
import androidx.navigation.compose.composable


@Serializable
data object ListadoDestination

//es la que llama a la ruta
fun NavController.navigateToListadoScreen(
    destination: ListadoDestination,
    navOptions: NavOptions? = null)
{
    this.navigate(destination, navOptions)
}


fun NavGraphBuilder.listadoScreen(
    onCharClick: (Int) -> Unit
){
    composable<ListadoDestination> {
        ListadoRoute(
            onCharClick = onCharClick
        )

    }
}

