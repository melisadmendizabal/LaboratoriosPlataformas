package com.uvg.laboratorio8.presentation.listadoLocalizacion

import com.uvg.laboratorio8.data.model.Location

data class ListadoLocalizacionState(
    val data: List<Location>? = null,
    val loading: Boolean = false,
    val hasError: Boolean = false
)
