package com.uvg.laboratorio8.presentation.localizacion

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.uvg.laboratorio8.data.sourse.LocationDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocalizacionViewModel (
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val locationDb = LocationDb()
    private var errorActivo = false
    private val locationProfile = savedStateHandle.toRoute<LocalizacionDestination>()
    private val _uiState: MutableStateFlow<LocalizacionState> = MutableStateFlow(LocalizacionState())
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


    fun obtenerDatosLocalizacion() {
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

            val localizacion =  locationDb.getLocationById(locationProfile.id)

            if (errorActivo){
                return@launch
            }

            _uiState.update { state ->
                state.copy(
                    data = localizacion,
                    loading = false,
                    hasError = false // Solo cuando los datos se obtienen correctamente
                )
            }
        }
    }
}