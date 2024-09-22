package com.uvg.laboratorio8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.uvg.laboratorio8.ui.bottomNavigation.BottomNavigationItem
import com.uvg.laboratorio8.ui.inicio.InicioDestination
import com.uvg.laboratorio8.ui.inicio.inicioScreen
import com.uvg.laboratorio8.ui.listado.ListadoDestination
import com.uvg.laboratorio8.ui.listado.listadoScreen
import com.uvg.laboratorio8.ui.listado.navigateToListadoScreen
import com.uvg.laboratorio8.ui.listadoLocalizacion.ListadoLocalizacionDestination
import com.uvg.laboratorio8.ui.listadoLocalizacion.listadoLocalizacionScreen
import com.uvg.laboratorio8.ui.listadoLocalizacion.navigateToListadoLocalizacionScreen
import com.uvg.laboratorio8.ui.localizacion.LocalizacionDestination
import com.uvg.laboratorio8.ui.localizacion.localizationScreen
import com.uvg.laboratorio8.ui.localizacion.navigateToLocalizationScreen
import com.uvg.laboratorio8.ui.perfil.PerfilDestination
import com.uvg.laboratorio8.ui.perfil.navigateToInicioScreen
import com.uvg.laboratorio8.ui.perfil.perfilScreen
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
                var estaInciada by remember { mutableStateOf(false) }
                val navController = rememberNavController()
                val menuItems = listOf(
                    BottomNavigationItem(
                        title = "Character",
                        unselectedIcon = Icons.Outlined.Face,
                        selectedIcon = Icons.Filled.Face,
                        hasNews = false,
                        badgeCount = null,
                        //ver esto
                        destination = ListadoDestination
                    ),

                    BottomNavigationItem(
                        title = "Location",
                        unselectedIcon = Icons.Outlined.LocationOn,
                        selectedIcon = Icons.Filled.LocationOn,
                        hasNews = false,
                        badgeCount = null,
                        destination = ListadoLocalizacionDestination
                    ),

                    BottomNavigationItem(
                        title = "Perfil",
                        unselectedIcon = Icons.Outlined.AccountCircle,
                        selectedIcon = Icons.Filled.AccountCircle,
                        hasNews = false,
                        badgeCount = null,
                        destination = PerfilDestination
                    )
                )
                var indexbar by rememberSaveable{
                    mutableIntStateOf(0)
                }

                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if(estaInciada){
                            NavigationBar {
                                menuItems.forEachIndexed { index, item->
                                    NavigationBarItem(
                                        selected = indexbar == index,
                                        onClick = {
                                            indexbar = index
                                            navController.navigate(item.destination)
                                        },
                                        label = { Text(text = item.title) },
                                        icon = {
                                            BadgedBox(
                                                badge = {
                                                    if(item.badgeCount != null) {
                                                        Badge {
                                                            Text(text = item.badgeCount.toString())
                                                        }
                                                    } else if(item.hasNews){
                                                        Badge()
                                                    }
                                                }
                                            ) {
                                                Icon(
                                                    imageVector = if (index == indexbar){
                                                        item.selectedIcon
                                                    } else item.unselectedIcon,
                                                    contentDescription = item.title
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        }

                    }) { innerPadding ->





                    NavHost(
                        navController = navController,
                        startDestination = InicioDestination,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding))
                    {
                        //llama a la primera función que se encuentra en el Navigation xd
                        inicioScreen(
                            //acá se declara que pasa con qué
                            onEntrarClick = {
                                estaInciada = true
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

                        perfilScreen(
                            onCerrarClick = {
                                estaInciada = false
                                navController.navigateToInicioScreen()


                            }
                        )

                        listadoLocalizacionScreen(
                            onLocaClick = { a ->
                                navController.navigateToLocalizationScreen(
                                    destination = LocalizacionDestination(id = a)
                                )

                            }
                        )

                        localizationScreen(
                            onNavigationLocaBack = {
                                navController.navigateToListadoLocalizacionScreen(
                                    destination = ListadoLocalizacionDestination,
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



