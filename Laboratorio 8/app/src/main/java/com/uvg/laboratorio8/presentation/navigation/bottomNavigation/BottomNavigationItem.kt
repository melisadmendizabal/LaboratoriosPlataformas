package com.uvg.laboratorio8.presentation.navigation.bottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import com.uvg.laboratorio8.presentation.listado.ListadoDestination
import com.uvg.laboratorio8.presentation.listadoLocalizacion.ListadoLocalizacionDestination
import com.uvg.laboratorio8.presentation.perfil.PerfilDestination

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val destination: Any
)

val menuNavItems = listOf(
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

val topLevelDestinations = listOf(
    ListadoDestination:: class,
    ListadoLocalizacionDestination:: class,
    PerfilDestination :: class
)
