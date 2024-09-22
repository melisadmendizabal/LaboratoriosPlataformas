package com.uvg.laboratorio8.ui.listadoLocalizacion

import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uvg.laboratorio8.LocationDb

@Composable
fun ListadoLocalizacionRoute(
    onLocaClick: (Int) -> Unit
){
    val locationDB = LocationDb()
    ListadoLocalizacionScreen(locationDB,onLocaClick = onLocaClick)


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoLocalizacionScreen(locationDb: LocationDb,
                              onLocaClick: (Int) -> Unit
){
    Column{
        TopAppBar(
            title = { Text(text = "Locations") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier.padding(20.dp)

        ) {
            items(locationDb.getAllLocations()){ locations ->
                ItemLocation(
                    modifier = Modifier
                        .clickable { onLocaClick(locations.id) },
                    nombre = locations.name,
                    tipo = locations.type
                )

            }

        }
    }
}

@Composable
fun ItemLocation(modifier: Modifier, nombre: String, tipo: String){
    Box(modifier = modifier.padding(vertical = 10.dp)){
        Column {
            Text(text = nombre,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
            Text(text = tipo)
        }
    }
}

