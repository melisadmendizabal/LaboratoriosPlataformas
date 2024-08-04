//Melisa Dayana Mendizabal Melendez
//23778
package com.uvg.laboratorio4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.uvg.laboratorio4.ui.theme.Laboratorio4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        FrontedBasico()
                        Noimagen()
                    }

                }
            }
        }
    }
}

@Composable
fun FrontedBasico(){
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .border(7.dp, Color(0xFF21891E))

        ){

        Image(
            painter = painterResource(id = R.drawable.escudo),
            contentDescription = "Logo de la Universidad del Valle",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .graphicsLayer(alpha = 0.3f)
        )


    }
}

@Composable
fun Noimagen(){
    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Universidad del Valle de Guatemala",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 8.em,
            lineHeight = 1.2.em,
            modifier = Modifier.fillMaxWidth(),

        )

        Text(text = "Programación de plataformas móviles, Sección 30",
            textAlign = TextAlign.Center,
            lineHeight = 1.3.em,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp),
            fontSize = 25.sp)

        Row(modifier = Modifier
            .fillMaxWidth(),){
            Text(text = "INTEGRANTES",
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )

            Column(modifier = Modifier.weight(0.5f)) {
                Text(text = "Melisa Mendizabal",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
                Text(text = "Renato Rojas",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
            }

        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp)){
            Text(text = "CATEDRÁTICO",
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold)
            Text(text = "Juan Carlos Durini",
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center)
        }

        Text(text = "Melisa Dayana Mendizabal Meléndez",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
        Text(text = "23778",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorio4Theme {
        FrontedBasico()
        Noimagen()
    }
}