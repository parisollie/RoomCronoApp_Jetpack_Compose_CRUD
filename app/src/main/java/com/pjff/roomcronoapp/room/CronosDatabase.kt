package com.pjff.roomcronoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pjff.roomcronoapp.model.Cronos

/*Vid 117,Inyecccion de dependencias,tratamos de administrar las dependencias
con los metoódos o clases para itegrarlas a otras clases, sin nececidad de hacer otra instancia
y las pasamos como parámetros */

/*Esto será una clase abstracta ,es una especie de super class, que no ser instanciada,pero
sus metódos si*/

//La version de nuestra base de datos siempre será la 1
@Database(entities = [Cronos::class], version = 1, exportSchema = false)
abstract class CronosDatabase: RoomDatabase() {
    //apunta al DAO que acabamos de creer.
    abstract fun cronosDao() : CronosDatabaseDao
}





