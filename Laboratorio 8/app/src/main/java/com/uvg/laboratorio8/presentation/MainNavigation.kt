package com.uvg.laboratorio8.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
data object MainNavigationGraph

fun NavController.navigateToMainGraph(navOptions: NavOptions? = null) {
    this.navigate(MainNavigationGraph, navOptions)
}

fun NavGraphBuilder.mainNavigationGraph(
    onCerrarClick: () -> Unit,
) {
    composable<MainNavigationGraph> {
        val nestedNavController = rememberNavController()
        MainScreen(
            navController = nestedNavController,
            onCerrarClick = onCerrarClick
        )
    }
}