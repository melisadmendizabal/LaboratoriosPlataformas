package com.uvg.laboratorio8.presentation.localizacion

import com.uvg.laboratorio8.data.model.Location

data class LocalizacionState(
    val data: Location? = null,
    val loading: Boolean = false,
    val hasError: Boolean = false
)