package com.uvg.laboratorio8.presentation.navigation.bottomNavigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon


@Composable
fun BottomNavBar(
    checkItemSelected: (Any) -> Boolean,
    onNavItemClick: (Any) -> Unit
){
    NavigationBar {
        menuNavItems.forEach{ navItem ->
            val isItemSelected = checkItemSelected(navItem.destination)
            NavigationBarItem(
                selected = isItemSelected,
                label = {Text(navItem.title)},
                onClick = {
                    onNavItemClick(navItem.destination)
                },
                icon = {
                    Icon(
                        imageVector = if (isItemSelected) {
                            navItem.selectedIcon
                        } else navItem.unselectedIcon,
                        contentDescription = navItem.title
                    )
                }
            )
        }
    }
}
