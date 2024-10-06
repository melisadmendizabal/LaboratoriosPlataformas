package com.uvg.laboratorio8.presentation.inicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.laboratorio8.R
import com.uvg.laboratorio8.presentation.ui.theme.Laboratorio8Theme

//Recuerda siempre crear una carpeta en UI para cada pantalla
//Una buena práctica es tener la función que conecta todo lo visual de la pantalla

//manejo del estado
//la variable un click se ultiliza pasa saber que va a pasar, en este caso se queda vacío
@Composable
fun InicioRoute(
    onEntrarClick:() -> Unit
){
    InicioScreen(
        onEntrarClick = onEntrarClick
    )
}

//solo miro xd
@Composable
private fun InicioScreen(
    onEntrarClick:() -> Unit
){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Column(modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.rick),
                contentDescription = "imagen",
                contentScale = ContentScale.Inside

            )
            Button(onClick = {onEntrarClick()}) {
                Text(text = "Entrar")

            }

        }
        Text(text = "Melisa Mendizabal Melendez - #23778",
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp))

    }
}

@Preview
@Composable
private fun PreviewInicioScreen(){
    Laboratorio8Theme {
        Surface {
            InicioScreen(
                onEntrarClick = {}
            )
        }
    }
}