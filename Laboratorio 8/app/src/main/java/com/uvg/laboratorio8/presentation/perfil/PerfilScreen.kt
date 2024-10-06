package com.uvg.laboratorio8.presentation.perfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uvg.laboratorio8.R

@Composable
fun PerfilRoute(
    onCerrarClick: () -> Unit
){
    PerfilScreen(
        onCerrarClick = onCerrarClick
    )

}
@Composable
fun PerfilScreen(
    onCerrarClick: () -> Unit
){

    Column(verticalArrangement = Arrangement.spacedBy(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(10.dp)
            ){
        Box(){
            Image(painter = painterResource(id = R.drawable.fotoperfil),
                contentDescription = "foto perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape)
            )
        }

        Row(modifier = Modifier.padding(horizontal = 40.dp)){
            Column(modifier = Modifier.weight(0.5f)) {
                Text(text = "Nombre: ")
                Text(text = "Carné: ")
            }

            Column(modifier = Modifier.weight(0.5f)) {
                Text(text = "Melisa Mendizabal",
                    textAlign = TextAlign.End)
                Text(text = "23778",
                    textAlign = TextAlign.End)
            }
        }


        Button(onClick = {onCerrarClick()}) {
            Text(text = "Cerrar Sesión")
        }
    }
}

