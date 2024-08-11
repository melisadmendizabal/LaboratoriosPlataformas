//Melisa Dayana Mendizabal Melendez
//23778
package com.uvg.laboratorio5

import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.Icons
import android.os.Bundle
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.laboratorio5.ui.theme.Laboratorio5Theme

import androidx.compose.material3.Text as Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        VistaGeneral()
                    }
                }
            }
        }
    }
}

@Composable
fun VistaGeneral(){
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()
        ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFf6cae8))
            .padding(
                horizontal = 10.dp,
                vertical = 10.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Refresh,
                contentDescription = "Actualizar",
                tint = Color(0xFF720850),
                modifier = Modifier.weight(0.1f)
                
            )
            Text(
                text = "Actualización disponible",
                modifier = Modifier.weight(0.6f),
                fontSize = 15.sp,
                color = Color(0xFF720850)
            )
            Button(onClick = {val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=com.GeorgeBatchelor.BirdAlone"))
                context.startActivity(intent)
            },colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            
            ) {
                Text(text = "Descargar",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,
                    color = Color(0xFF681766)
                )
                
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)){
            Column(modifier = Modifier
                .weight(0.5f)
                .padding(horizontal = 15.dp)){
                Text(text = "Martes",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF720850)
                )

               Text(text = "23 de enero",
                   color = Color(0xFF720850))
            }

            Column(modifier = Modifier
                .weight(0.5f)
                .padding(horizontal = 20.dp)
                .align(Alignment.Bottom)
            ){
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = BorderStroke(2.dp,Color(0xFF720850))
                ) {
                    Text(text = "Terminar jornada",
                        color = Color(0xFF720850))
                }
            }
        }


        OutlinedCard(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
            onClick = { /*TODO*/ }) {
            Column(modifier = Modifier.padding(10.dp)){
                Row(verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Trefratelli",
                        color = Color(0xFF720850),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(0.9f)
                        )
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://maps.app.goo.gl/7wZRkQqBULg6BM1x6"),
                            )
                        context.startActivity(intent)
                    },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFf6cae8)),

                        ) {
                           Icon(Icons.Outlined.LocationOn,
                               contentDescription = "Ubicacion",
                               tint = Color(0xFF720850))
                    }
                }
                Text(text = "Paseo Cayalá - Edificio F1, 05.",
                    color = Color(0xFF720850))
                Text(text = "7:00 a.m. - 10:30 p.m.",
                    color = Color(0xFF720850))
            }

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(onClick = {
                    Toast.makeText(
                        context,
                    "Melisa Dayana Mendizabal Melendez",
                        Toast.LENGTH_LONG
                    ).show()
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFf6cae8)),

                    modifier = Modifier
                        .weight(0.5f)
                        .padding(horizontal = 10.dp)) {
                    Text(text = "Iniciar",
                        color = Color(0xFF720850))

                }
                Button(onClick = {
                    Toast.makeText(
                        context,
                        "Comida Italiana\nQQQ",
                        Toast.LENGTH_LONG
                    ).show()
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = BorderStroke(2.dp, color = Color(0xFF720850)),
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(10.dp)) {
                    Text(text = "Detalles",
                        color = Color(0xFF720850))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorio5Theme {
        VistaGeneral()
    }
}