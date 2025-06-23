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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
//Vid 123
class CronometroViewModel @Inject constructor(private val repository: CronosRepository) : ViewModel() {

    var state by mutableStateOf(CronoState())
        private set

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

    //Vid 123
    fun onValue(value:String){
        state = state.copy(title = value)
    }

    //Vid 124, metódos
    fun iniciar(){
        state = state.copy(
            cronometroActivo = true
        )
    }
    //Vid 124,
    fun pausar(){
        state = state.copy(
            cronometroActivo = false,
            showSaveButton = true
        )
    }
    //Vid 124,
    fun detener(){
        cronoJob?.cancel()
        tiempo = 0

        state = state.copy(
            cronometroActivo = false,
            showSaveButton = false,
            showTextField = false,
            title = ""
        )
    }

    //Vid 124,
    fun showTextField(){
        state = state.copy(
            showTextField = true
        )
    }


    //Vid 124,
    fun cronos(){
        if(state.cronometroActivo){
            cronoJob?.cancel()
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