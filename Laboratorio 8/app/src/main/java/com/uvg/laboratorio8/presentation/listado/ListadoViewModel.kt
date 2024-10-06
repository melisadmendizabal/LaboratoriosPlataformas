package com.uvg.laboratorio8.presentation.listado


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.laboratorio8.data.sourse.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListadoViewModel: ViewModel() {
    private val characterDb = CharacterDb()
    private var errorActivo = false
    private val _uiState: MutableStateFlow<ListadoState> = MutableStateFlow(ListadoState())
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

    fun obtenerListadoPersonaje() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    loading = true,
                    hasError = false
                )
            }
            delay(4000)

            if (errorActivo){
                return@launch
            }

            val listadoLocalizacion =  characterDb.getAllCharacters()

            if (errorActivo){
                return@launch
            }

            _uiState.update { state ->
                state.copy(
                    data = listadoLocalizacion,
                    loading = false,
                    hasError = false // Solo cuando los datos se obtienen correctamente
                )
            }
        }
    }

}