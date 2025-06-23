package com.pjff.roomcronoapp.state

//Paso 6.2
data class CronoState(
    //Para iniciarlo
    val cronometroActivo : Boolean = false,
    //Cuando pausemos el cronómetro
    val showSaveButton: Boolean = false,
    //Mostrar el textField
    val showTextField : Boolean = false,
    val title : String = ""
)
