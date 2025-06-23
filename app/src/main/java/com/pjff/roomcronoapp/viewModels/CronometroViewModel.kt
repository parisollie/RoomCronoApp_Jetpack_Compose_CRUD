package com.pjff.roomcronoapp.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pjff.roomcronoapp.repository.CronosRepository
import com.pjff.roomcronoapp.state.CronoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
//Paso 6.3, para manera la logica del cronómetro
class CronometroViewModel @Inject constructor(private val repository: CronosRepository) : ViewModel() {

    var state by mutableStateOf(CronoState())
        private set
    //Job nos permite controlar la corrutina ,para la reproduccion
    var cronoJob by mutableStateOf<Job?>(null)
        private set
    //0L representa un numero largo
    var tiempo by mutableStateOf(0L)
        private set

    fun getCronoById(id:Long){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCronoById(id).collect { item ->
                if(item != null){
                    tiempo = item.crono
                    state = state.copy(title = item.title)
                }else{
                    Log.d("Error", "El objeto crono es nulo")
                }
            }
        }
    }

    fun onValue(value:String){
        state = state.copy(title = value)
    }

    //V-125, Paso 6.4 Funciones del cronometro
    fun iniciar(){
        state = state.copy(
            cronometroActivo = true
        )
    }

    fun pausar(){
        state = state.copy(
            cronometroActivo = false,
            showSaveButton = true
        )
    }

    fun detener(){
        //Para que se cancele el proceso
        cronoJob?.cancel()
        // Para que el cronometro se iguale a cero
        tiempo = 0

        state = state.copy(
            cronometroActivo = false,
            showSaveButton = false,
            showTextField = false,
            title = ""
        )
    }

    fun showTextField(){
        state = state.copy(
            showTextField = true
        )
    }

    //Función principal
    fun cronos(){
        //Si esta activicio inicia la secuencia
        if(state.cronometroActivo){
            cronoJob?.cancel()
            //se ejecuta en el hilo principal
            cronoJob = viewModelScope.launch {
                while (true){
                    delay(1000)
                    tiempo += 1000
                }
            }
        }else{
            cronoJob?.cancel()
        }
    }


}