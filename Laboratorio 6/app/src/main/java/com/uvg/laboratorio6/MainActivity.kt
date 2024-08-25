package com.uvg.laboratorio6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.laboratorio6.ui.theme.Laboratorio6Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio6Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)){
                        PantallaPrincipal()
                    }
                }
            }
        }
    }
}


@Composable
fun PantallaPrincipal(){
    var contador by remember {mutableStateOf(0)}
    var incrementos by remember { mutableStateOf(0) }
    var decrementos by remember { mutableStateOf(0) }
    var maximo by remember { mutableStateOf(0) }
    var minimo by remember { mutableStateOf(0) }
    var cambios by remember { mutableStateOf(0) }
    var listaCambios by remember { mutableStateOf(listOf<Pair<Int, Color>>()) }

    var colorfondo by remember { mutableStateOf( Color(0xFFFFFFFF)) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp)) {

        Text(text = "Melisa Dayana\nMendizabal Melendez",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            lineHeight = 30.sp
        )

        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(vertical = 12.dp)){
            Button(onClick = {
                contador--          // Disminuir el valor del contador
                decrementos++
                cambios++
                listaCambios = listaCambios + Pair(contador, Color(0xFFE83845))
                colorfondo = Color(0xFFE83845)
                if(minimo > contador){
                    minimo = contador
                }
            },
                shape = CircleShape,
                modifier = Modifier.size(50.dp)
            ){
                Text(text = "-",
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp)
            }

            Text(text = "$contador",
                fontSize = 35.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 10.dp))

            Button(onClick = {
                contador++
                incrementos++
                cambios++
                listaCambios = listaCambios + Pair(contador, Color(0xFF4DD091))
                colorfondo = Color(0xFF4DD091)
                if(maximo < contador) {
                    maximo = contador
                }
            },
            ){
                Text(text = "+",
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp)
            }
        }


        Descripcion(descripcion = "Total incrementos:", valor = incrementos)
        Descripcion(descripcion = "Total decrementos:", valor = decrementos)
        Descripcion(descripcion = "Valor máximo:", valor = maximo)
        Descripcion(descripcion = "Valor mínimo:", valor = minimo)
        Descripcion(descripcion = "Total cambios:", valor = cambios)

        Text(text = "Historial:",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold)
        LazyColumn(modifier = Modifier.fillMaxWidth().weight(7f),
            verticalArrangement = Arrangement.spacedBy(5.dp)) {

            items(listaCambios.chunked(5)){ fila ->
                Row {
                    fila.forEach { (numero, color) ->
                        elementoContador(color = color, valor = numero)
                    }
                }
            }
        }

        Button(onClick = {
            listaCambios = listOf()
            contador = 0
            incrementos = 0
            decrementos = 0
            maximo = 0
            minimo = 0
            cambios = 0
        },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f))

        {
            Text(text = "Reiniciar")
        }

    }
}

@Composable
fun elementoContador(color: Color, valor: Int){

    Box(modifier = Modifier
        .clip(RectangleShape)
        .width(70.dp)
        .padding(5.dp)
        .background(color)

    ) {
        Text(text = valor.toString(),
            modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun Descripcion(descripcion: String, valor: Int){

    Row(modifier = Modifier
        .padding(vertical = 2.dp)) {
        Text(text = descripcion,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.9f)
        )
        Text(text = "$valor",
            fontSize = 20.sp,
            modifier = Modifier.weight(0.1f))

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorio6Theme {
        PantallaPrincipal()
    }
}
