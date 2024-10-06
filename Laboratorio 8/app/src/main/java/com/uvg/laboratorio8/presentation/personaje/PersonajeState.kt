package com.uvg.laboratorio8.presentation.personaje

import com.uvg.laboratorio8.data.model.Character

data class PersonajeState(
    val data: Character? = null,
    val loading: Boolean = false,
    val hasError: Boolean = false
)