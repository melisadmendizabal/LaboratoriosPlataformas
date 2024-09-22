package com.uvg.laboratorio8.ui.localizacion

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.uvg.laboratorio8.ui.bottomNavigation.BNDestination
import kotlinx.serialization.Serializable

@Serializable
data class LocalizacionDestination(
    val id: Int
)

fun NavController.navigateToLocalizationScreen(
    destination: LocalizacionDestination,
    navOptions: NavOptions? = null
){
    this.navigate(destination,navOptions)
}

fun NavGraphBuilder.localizationScreen(
    onNavigationLocaBack: () -> Unit
){
    composable<LocalizacionDestination>{backStackEntry ->
        val destination: LocalizacionDestination = backStackEntry.toRoute()
        LocalizacionRoute(
            id = destination.id,
            onNavigationLocaBack = onNavigationLocaBack
            )
    }
}