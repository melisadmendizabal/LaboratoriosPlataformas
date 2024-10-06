package com.uvg.laboratorio8.presentation.listado

import com.uvg.laboratorio8.data.model.Character

data class ListadoState(
    val data: List<Character>? = null,
    val loading: Boolean = false,
    val hasError: Boolean = false
)
