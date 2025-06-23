package com.pjff.roomcronoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pjff.roomcronoapp.model.Cronos

/*V-117,Paso 2.2

Inyecccion de dependencias -> Tratamos de administrar las dependencias
con los metódos o clases para integrarlas a otras clases, sin nececidad de hacer
otra instancia dentro de una de calse y la pasamos como parámetros.  */

/*Esto será una clase abstracta ,es una especie de super class, que no puede ser
instanciada,pero sus metódos si*/

/*Le ponemos nuestra entidad ( la unica que tenemos Cronos)
La versión de nuestra base de datos siempre será la 1 al principio */
@Database(entities = [Cronos::class], version = 1, exportSchema = false)
abstract class CronosDatabase: RoomDatabase() {
    //apunta al DAO que acabamos de crear y retorna un CronosDatabaseDao.
    abstract fun cronosDao() : CronosDatabaseDao
}





