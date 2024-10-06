package com.uvg.laboratorio8.presentation.personaje

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.uvg.laboratorio8.data.sourse.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PersonajeViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val characterDb = CharacterDb()
    private var errorActivo = false
    private val personajePerfil = savedStateHandle.toRoute<PersonajeDestination>()
    private val _uiState: MutableStateFlow<PersonajeState> = MutableStateFlow(PersonajeState())
    val uiState = _uiState.asStateFlow()

    fun reiniciarEstado(){
        errorActivo = false
        _uiState.update { state ->
            state.copy(
                loading = false,
                hasError = true,
                data = null
            )
        }

    }

    fun onError() {
        errorActivo = true
        _uiState.update { state ->
            state.copy(
                loading = false,
                hasError = true
            )
        }
    }


    fun obtenerDatosPersonajes() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    loading = true,
                    hasError = false
                )
            }
            delay(2000)

            if (errorActivo){
                return@launch
            }

            val localizacion =  characterDb.getCharacterById(personajePerfil.id)

            if (errorActivo){
                return@launch
            }

            _uiState.update { state ->
                state.copy(
                    data = localizacion,
                    loading = false,
                    hasError = false
                )
            }
        }
    }

}