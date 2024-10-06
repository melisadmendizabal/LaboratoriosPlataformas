package com.uvg.laboratorio8.presentation.perfil
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object PerfilDestination

fun NavGraphBuilder.perfilScreen(
    onCerrarClick: () -> Unit
){
    composable<PerfilDestination>{
        PerfilRoute(
            onCerrarClick = onCerrarClick
        )
    }

}

