package com.pjff.roomcronoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pjff.roomcronoapp.model.Cronos
import kotlinx.coroutines.flow.Flow

/*
   V-116,Paso 2.1 creamos una interface para definir metódos en otras clases

   Este es el camino que seguiremos:
     Interface -> Repositorio -> Viewmodel -> View

   Data Access Observer, es el observador de acceso de datos.
*/
@Dao
interface CronosDatabaseDao {

    // Crud -> CREATE-READ-UPDATE-DELETE
    @Query("SELECT * FROM cronos")
    // La query esta ligada  a un metódo fun y nos retorna un flow ,es tipo list, lo queremos en una lista.
    fun getCronos(): Flow<List<Cronos>>

    // Mostrar sólo un registro
    @Query("SELECT * FROM cronos WHERE id = :id ")
    // Retornamos un Flow tipo cronos, nuestro id es de tipo long.
    fun getCronosById(id: Long): Flow<Cronos>

    //En caso de que tengas problemas al guardar le ponemos REPLACE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(crono: Cronos)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(crono: Cronos)

    @Delete
    suspend fun delete(crono : Cronos)

}