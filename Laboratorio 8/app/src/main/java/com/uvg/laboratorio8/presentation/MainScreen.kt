package com.uvg.laboratorio8.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uvg.laboratorio8.presentation.localizacion.localizacionGraph
import com.uvg.laboratorio8.presentation.navigation.bottomNavigation.BottomNavBar
import com.uvg.laboratorio8.presentation.navigation.bottomNavigation.topLevelDestinations
import com.uvg.laboratorio8.presentation.perfil.perfilScreen
import com.uvg.laboratorio8.presentation.personaje.PersonajesNavGraph
import com.uvg.laboratorio8.presentation.personaje.personajesGraph
import com.uvg.laboratorio8.presentation.ui.theme.Laboratorio8Theme


@Composable
fun MainScreen(
    onCerrarClick: () -> Unit,
    navController: NavHostController = rememberNavController())
{
    var bottomBarVisible by rememberSaveable {
        mutableStateOf(false)
    }

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    bottomBarVisible = if (currentDestination != null) {
        topLevelDestinations.any {destination ->
            currentDestination.hasRoute(destination)
        }
    } else {
        false
    }

    Scaffold(

        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarVisible,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                BottomNavBar(
                    checkItemSelected = { destination ->
                        currentDestination?.hierarchy?.any { it.hasRoute(destination::class) }
                            ?: false
                    },
                    onNavItemClick = { destination ->
                        navController.navigate(destination) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = PersonajesNavGraph,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            personajesGraph(navController)
            localizacionGraph(navController)
            perfilScreen(
                onCerrarClick = onCerrarClick
            )
        }
    }
}

@Preview
@Composable
private fun Ausadha(){
    Laboratorio8Theme {
        MainScreen(onCerrarClick = { /*TODO*/ })
    }
}