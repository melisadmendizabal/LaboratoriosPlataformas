package com.uvg.laboratorio8.presentation.personaje

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


@Serializable
data class PersonajeDestination(
    val id: Int
)

fun NavController.navigateToPersonajeScreen(
    id: Int,
    navOptions: NavOptions? = null
){
    this.navigate(
        route = PersonajeDestination(id = id),
        navOptions = navOptions
    )   //aaaaaa
}

fun NavGraphBuilder.personajeScreen(
    onNavigateBack: () -> Unit
){
    composable<PersonajeDestination>{ backStackEntry ->
        val destination: PersonajeDestination = backStackEntry.toRoute()
        PersonajeRoute(
            onNavigateBack = onNavigateBack
        )
    }
}