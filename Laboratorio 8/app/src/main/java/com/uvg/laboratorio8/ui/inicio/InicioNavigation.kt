package com.uvg.laboratorio8.ui.inicio

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uvg.laboratorio8.ui.bottomNavigation.BNDestination
import kotlinx.serialization.Serializable

@Serializable
data object InicioDestination: BNDestination

fun NavGraphBuilder.inicioScreen(
    onEntrarClick: ()-> Unit
){
    composable<InicioDestination>{
        InicioRoute(
            onEntrarClick = onEntrarClick

        )
    }
}