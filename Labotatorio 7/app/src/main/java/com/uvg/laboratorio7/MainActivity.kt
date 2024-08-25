package com.uvg.laboratorio7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.laboratorio7.ui.theme.Laboratorio7Theme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio7Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        PantallaPrincipal()
                    }
                }
            }
        }
    }
}


enum class NotificationType {
    GENERAL,
    NEW_POST,
    NEW_MESSAGE,
    NEW_LIKE
}

data class Notification(
    val id: Int,
    val title: String,
    val body: String,
    val sendAt: Date,
    val type: NotificationType
)

fun LocalDateTime.toDate(): Date {
    return Date.from(this.atZone(java.time.ZoneId.systemDefault()).toInstant())
}

fun generateFakeNotifications(): List<Notification> {
    val notifications = mutableListOf<Notification>()
    val titles = listOf(
        "Nueva versión disponible",
        "Nuevo post de Juan",
        "Mensaje de Maria",
        "Te ha gustado una publicación"
    )
    val bodies = listOf(
        "La aplicación ha sido actualizada a v1.0.2. Ve a la PlayStore y actualízala!",
        "Te han etiquetado en un nuevo post. ¡Míralo ahora!",
        "No te olvides de asistir a esta capacitación mañana, a las 6pm, en el Intecap.",
        "A Juan le ha gustado tu publicación. ¡Revisa tu perfil!"
    )
    val types = NotificationType.entries.toTypedArray()

    val currentDate = LocalDate.now()
    for (i in 1..50) {
        val daysAgo = (0..10).random()
        val hoursAgo = (0..23).random()
        val minutesAgo = (0..59).random()
        val sendAt = LocalDateTime.of(currentDate.minusDays(daysAgo.toLong()), LocalTime.of(hoursAgo, minutesAgo)).toDate()
        notifications.add(
            Notification(
                id = i,
                title = titles.random(),
                body = bodies.random(),
                sendAt = sendAt,
                type = types.random()
            )
        )
    }
    return notifications
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(modifier: Modifier = Modifier){
    val listaNotificacion = generateFakeNotifications()
    var filtroSeleccionado by remember { mutableStateOf<NotificationType?>(null) }
    val tipos = NotificationType.values()

    Column(modifier) {
        TopAppBar(
            title = { Text(text = "Notificaciones") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "Back"
                    )

                }
            }
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Tipos de Notificaciones",
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ){
                items(tipos) { type ->
                    FilterChip(
                        selected = filtroSeleccionado == type,
                        onClick = { filtroSeleccionado = type },
                        label = { Text(type.name) },
                        modifier = Modifier.padding(4.dp)
                    )
                }

            }
            val listaFiltrada = listaNotificacion.filter {
                filtroSeleccionado == null || it.type == filtroSeleccionado
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(7.dp),
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                items(listaFiltrada) {noti ->

                    CuerpoNotificacion(
                        titulo = noti.title,
                        fecha = noti.sendAt.toString(),
                        descripcion = noti.body,
                        tipoNoti = noti.type
                    )
                }
            }
        }
    }
}



@Composable
fun CuerpoNotificacion(titulo: String, fecha:String, descripcion:String, tipoNoti: NotificationType){
    val icon = when (tipoNoti){
        NotificationType.NEW_LIKE -> Icons.Filled.FavoriteBorder
        NotificationType.NEW_MESSAGE -> Icons.Filled.Email
        NotificationType.NEW_POST -> Icons.Filled.AccountBox
        NotificationType.GENERAL -> Icons.Filled.Build
    }
    val iconColorfondo = when (tipoNoti){
        NotificationType.GENERAL -> MaterialTheme.colorScheme.primary
        NotificationType.NEW_MESSAGE -> MaterialTheme.colorScheme.secondary
        NotificationType.NEW_POST -> MaterialTheme.colorScheme.onSecondary
        NotificationType.NEW_LIKE -> MaterialTheme.colorScheme.error
    }

    val iconColor = when (tipoNoti){
        NotificationType.GENERAL -> MaterialTheme.colorScheme.onPrimary
        NotificationType.NEW_MESSAGE -> MaterialTheme.colorScheme.onSecondary
        NotificationType.NEW_POST -> MaterialTheme.colorScheme.secondary
        NotificationType.NEW_LIKE -> MaterialTheme.colorScheme.onError
    }


    Box {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            ){
            Box(modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(iconColorfondo)
                .align(Alignment.CenterVertically),
                contentAlignment = Alignment.Center
                ){

                Icon(imageVector = icon,
                    contentDescription = "si",
                    tint =iconColor
                )
            }

            Column(modifier = Modifier
                .weight(8f)
                .padding(horizontal = 15.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = titulo,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        modifier = Modifier.weight(0.6f)
                    )

                    Text(text = fecha,
                        textAlign = TextAlign.Right,
                        fontSize = 10.sp,
                        modifier = Modifier.weight(0.4f)
                    )
                }
                Text(text= descripcion,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorio7Theme {
        PantallaPrincipal(
            modifier = Modifier.fillMaxSize()
        )
    }
}