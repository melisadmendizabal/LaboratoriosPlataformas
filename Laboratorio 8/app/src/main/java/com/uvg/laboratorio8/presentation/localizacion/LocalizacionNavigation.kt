package com.uvg.laboratorio8.presentation.localizacion

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class LocalizacionDestination(
    val id: Int
)


fun NavController.navigateToLocalizationScreen(
    id: Int,
    navOptions: NavOptions? = null
){
    this.navigate(
        route = LocalizacionDestination(id = id),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.localizationScreen(
    onNavigationLocaBack: () -> Unit
){
    composable<LocalizacionDestination>{
        LocalizacionRoute(
            onNavigationLocaBack = onNavigationLocaBack
        )
    }
}