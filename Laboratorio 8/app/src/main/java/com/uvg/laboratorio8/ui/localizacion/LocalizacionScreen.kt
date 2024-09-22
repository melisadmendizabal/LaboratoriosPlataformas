package com.uvg.laboratorio8.ui.localizacion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.laboratorio8.LocationDb

@Composable
fun LocalizacionRoute(
    id: Int,
    onNavigationLocaBack: () -> Unit
){
    val MyA = LocationDb()
    val IDLocalization = MyA.getLocationById(id)

    LocalizacionScreen(
        idd = id,
        name = IDLocalization.name,
        type = IDLocalization.type,
        dimension = IDLocalization.dimension,
        onNavigationLocaBack = onNavigationLocaBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocalizacionScreen(idd: Int, name: String, type:String, dimension:String,
                       onNavigationLocaBack: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        
        TopAppBar(
            title = { Text(text = "Location Details")},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            navigationIcon = {
                IconButton(onClick = onNavigationLocaBack) {
                    Icon(
                        Icons.Filled.Clear, contentDescription = "back"
                    )
                    
                }
            }
        )
        Text(text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Row(modifier = Modifier
            .padding(40.dp)){
            Column(modifier = Modifier.weight(0.5f)) {
                Text(text = "ID:")
                Text(text = "Type:")
                Text(text = "Dimensions:")

            }
            Column {
                Text(text = idd.toString(),
                    textAlign = TextAlign.End)
                Text(text = type,
                    textAlign = TextAlign.End)
                Text(text = dimension,
                    textAlign = TextAlign.End)
            }
        }
    }
}