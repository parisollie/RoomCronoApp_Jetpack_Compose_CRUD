package com.pjff.roomcronoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pjff.roomcronoapp.model.Cronos
import kotlinx.coroutines.flow.Flow


//Vid 115, creamos una interface para definir metodos en otras clases
// Interface -> Repositorio -> Viewmodel -> View
@Dao // Data Access Observer
interface CronosDatabaseDao {
    // Crud- CREATE-READ-UPDATE-DELETE
    @Query("SELECT * FROM cronos")
    //la query esta ligada  y nso retorna un flow ,es tipo list, lo queremos en una lista
    fun getCronos(): Flow<List<Cronos>>

    //Mostrar solo un registro
    @Query("SELECT * FROM cronos WHERE id = :id ")
    //retornamos un Flow tipo cronos.
    fun getCronosById(id: Long): Flow<Cronos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(crono: Cronos)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(crono: Cronos)

    @Delete
    suspend fun delete(crono : Cronos)



}