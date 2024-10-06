package com.uvg.laboratorio8.presentation.listadoLocalizacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.laboratorio8.data.sourse.LocationDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListadoLocalizacionViewModel: ViewModel() {
    private val locationDb = LocationDb()
    private var errorActivo = false
    private val _uiState: MutableStateFlow<ListadoLocalizacionState> = MutableStateFlow(ListadoLocalizacionState())
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


    fun obtenerListadolocalizacione() {
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

            val listadoLocalizacion =  locationDb.getAllLocations()

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