package com.uvg.laboratorio8.ui.perfil
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.uvg.laboratorio8.ui.bottomNavigation.BNDestination
import com.uvg.laboratorio8.ui.inicio.InicioDestination
import kotlinx.serialization.Serializable

@Serializable
data object PerfilDestination: BNDestination

fun NavGraphBuilder.perfilScreen(onCerrarClick: ()->Unit
){
    composable<PerfilDestination>{
        PerfilRoute(
            onCerrarClick = onCerrarClick
        )
    }

}

fun NavController.navigateToInicioScreen(
    navOptions: NavOptions? = null
){
    for (item in this.graph){
        this.popBackStack()
    }
    this.navigate(InicioDestination, navOptions)
}

