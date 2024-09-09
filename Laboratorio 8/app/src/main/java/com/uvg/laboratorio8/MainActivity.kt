package com.uvg.laboratorio8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.uvg.laboratorio8.ui.inicio.InicioDestination
import com.uvg.laboratorio8.ui.inicio.inicioScreen
import com.uvg.laboratorio8.ui.listado.ListadoDestination
import com.uvg.laboratorio8.ui.listado.listadoScreen
import com.uvg.laboratorio8.ui.listado.navigateToListadoScreen
import com.uvg.laboratorio8.ui.personaje.PersonajeDestination
import com.uvg.laboratorio8.ui.personaje.navigateToPersonajeScreen
import com.uvg.laboratorio8.ui.personaje.personajeScreen
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio8Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = InicioDestination,
                        modifier = Modifier.fillMaxSize().padding(innerPadding))
                    {
                        //llama a la primera función que se encuentra en el Navigation xd
                        inicioScreen(
                            //acá se declara que pasa con qué
                            onEntrarClick = {
                                //propiedad del navControlles y llamo a la función encargada de mostrar la pantalla desde el navigation
                                //de la otra pantalla
                                navController.navigateToListadoScreen(
                                    destination = ListadoDestination,
                                    navOptions = navOptions {
                                        popUpTo<InicioDestination>(){
                                            inclusive = true
                                        }
                                    })
                            }
                        )

                        listadoScreen(
                            onCharClick = { id ->
                                navController.navigateToPersonajeScreen(                         //AAAAAAAAAAAAA
                                    destination = PersonajeDestination(id = id)
                                )
                            }
                        )
                        personajeScreen(
                            onNavigateBack = {
                                navController.navigateToListadoScreen(
                                    destination = ListadoDestination,
                                    navOptions = navOptions {
                                        popUpTo(0)
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}



