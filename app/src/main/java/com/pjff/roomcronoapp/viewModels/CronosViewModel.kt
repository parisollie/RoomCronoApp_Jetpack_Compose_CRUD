package com.pjff.roomcronoapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pjff.roomcronoapp.model.Cronos
import com.pjff.roomcronoapp.repository.CronosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

//Vid 120 ,aqui estamos haciendo una cadena de inyeccion CronosRepository
@HiltViewModel
class CronosViewModel @Inject constructor(private val repository: CronosRepository): ViewModel() {

    //Aqui se descargan todos nuestros registros
    private val _cronosList = MutableStateFlow<List<Cronos>>(emptyList())
    val cronosList = _cronosList.asStateFlow()

    //Funcion para llamar todo
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCronos().collect { item ->
                if (item.isNullOrEmpty()){
                    _cronosList.value = emptyList()
                }else{
                    //Si tenemos algun registro.
                    _cronosList.value = item
                }
            }
        }
    }

    //Funciones para llamar los metodos
    fun addCrono(crono: Cronos) = viewModelScope.launch { repository.addCrono(crono) }
    fun updateCrono(crono: Cronos) = viewModelScope.launch { repository.updateCrono(crono) }
    fun deleteCrono(crono: Cronos) = viewModelScope.launch { repository.deleteCrono(crono) }

}