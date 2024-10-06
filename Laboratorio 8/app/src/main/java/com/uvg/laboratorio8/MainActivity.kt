package com.uvg.laboratorio8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.uvg.laboratorio8.presentation.navigation.AppNavigation
import com.uvg.laboratorio8.presentation.ui.theme.Laboratorio8Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            Laboratorio8Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)){
                        AppNavigation(
                            navController = navController
                        )
                    }




                }
            }
        }
    }
}



