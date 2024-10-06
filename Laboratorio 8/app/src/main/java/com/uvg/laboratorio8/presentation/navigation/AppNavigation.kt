package com.uvg.laboratorio8.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.laboratorio8.presentation.inicio.InicioDestination
import com.uvg.laboratorio8.presentation.inicio.inicioScreen
import com.uvg.laboratorio8.presentation.mainNavigationGraph
import com.uvg.laboratorio8.presentation.navigateToMainGraph


@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
){  //Parametros para la navegación

    NavHost(                                                                    //NavHost general que se llama en el main
        navController = navController,              //el nav de parametro
        startDestination = InicioDestination,       //la página de inicio
    ){

        inicioScreen(                   //lo mismo se llama a la pantalla y se mira qué funcion tiene el botón
            onEntrarClick = {
                navController.navigateToMainGraph(
                    navOptions = NavOptions.Builder().setPopUpTo<InicioDestination>(
                        inclusive = true
                    ).build()
                )
            }
        )


        mainNavigationGraph(
            onCerrarClick = {
                navController.navigate(InicioDestination) {
                    popUpTo(0)
                }
            }
        )







    }

}