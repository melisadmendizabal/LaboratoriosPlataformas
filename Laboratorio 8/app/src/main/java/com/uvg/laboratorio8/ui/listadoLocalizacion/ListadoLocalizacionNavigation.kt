package com.uvg.laboratorio8.ui.listadoLocalizacion

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.uvg.laboratorio8.ui.bottomNavigation.BNDestination
import kotlinx.serialization.Serializable

@Serializable
data object  ListadoLocalizacionDestination: BNDestination

fun NavController.navigateToListadoLocalizacionScreen(
    destination: ListadoLocalizacionDestination,
    navOptions: NavOptions? = null
){
    this.navigate(destination,navOptions)
}

fun NavGraphBuilder.listadoLocalizacionScreen(
    onLocaClick: (Int) -> Unit
){
    composable<ListadoLocalizacionDestination>{
        ListadoLocalizacionRoute(
            onLocaClick = onLocaClick
        )
    }
}