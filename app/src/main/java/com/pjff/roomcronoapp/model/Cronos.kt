package com.pjff.roomcronoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
  V-115,Paso 2.0 creamos nuestro data class y lo convertimos en una entidad ,
  Una entidad es una tabla Entidades = Tabla, Atributo = campo
*/
@Entity(tableName = "cronos")
data class Cronos(
    //Ponemos nuestra llave primaria.
    @PrimaryKey(autoGenerate = true)
    //long ,porqué no sabemos a que número llegaremos.
    val id : Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "crono")
    val crono : Long
)
