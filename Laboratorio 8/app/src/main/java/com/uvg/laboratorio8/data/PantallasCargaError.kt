package com.uvg.laboratorio8.data

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.laboratorio8.presentation.ui.theme.Laboratorio8Theme


@Composable
fun PantallaCarga(onCargando:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .clickable { onCargando() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Text(text = "Cargando")

    }
}


@Composable
fun PantallaError(nombrePantalla: String,
                  onReintentar:() -> Unit){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Warning,
            contentDescription = "Error",
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))

        Text(text = "Error al obtener $nombrePantalla",
            color = MaterialTheme.colorScheme.error)

        Text(text = "Intenta de nuevo",
            color = MaterialTheme.colorScheme.error)

        Spacer(modifier = Modifier.padding(5.dp))

        OutlinedButton(
            onClick = { onReintentar()},
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.error),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text(text = "Reintentar")
        }
    }

}


@Preview
@Composable
private fun PantallaCargaView(){
    Laboratorio8Theme {
        Surface {
            PantallaCarga(
                onCargando = { }
            )
        }
    }
}

@Preview
@Composable
private fun PantallaErrorView(){
    Laboratorio8Theme {
        Surface {
            PantallaError(nombrePantalla = "Pantalla",
                onReintentar = { })


        }
    }
}