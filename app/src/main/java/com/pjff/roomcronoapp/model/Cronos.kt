package com.pjff.roomcronoapp.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Vid 114, creamos nuestro data class y lo convertimos en una entidad ,una entidad es una tabla
// Entidades = Tabla, Atributo = campo
@Entity(tableName = "cronos")
data class Cronos(
    //Ponemos nuestra llave primaria.
    @PrimaryKey(autoGenerate = true)
    //long porque no sabemos a que numero llegaremos
    val id : Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "crono")
    val crono : Long
)
