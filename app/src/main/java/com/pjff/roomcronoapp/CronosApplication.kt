package com.pjff.roomcronoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//V-119,Paso 3.0, empezamos con la inyeccion de dependencias.
@HiltAndroidApp
//indicamos al manifest que tenemos esto
class CronosApplication: Application() {
}