package com.pjff.roomcronoapp.repository

import com.pjff.roomcronoapp.model.Cronos
import com.pjff.roomcronoapp.room.CronosDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class CronosRepository @Inject constructor(private val cronosDatabaseDao: CronosDatabaseDao) {

    //Vid 119, metódos que se usaran en el view MOdel y accedemos a los metódos de la interfaz
    suspend fun addCrono(crono: Cronos)     = cronosDatabaseDao.insert(crono)
    suspend fun updateCrono(crono: Cronos)  = cronosDatabaseDao.update(crono)
    suspend fun deleteCrono(crono: Cronos)  = cronosDatabaseDao.delete(crono)
    fun getAllCronos(): Flow<List<Cronos>>  = cronosDatabaseDao.getCronos().flowOn(Dispatchers.IO).conflate()
    fun getCronoById(id:Long): Flow<Cronos> = cronosDatabaseDao.getCronosById(id).flowOn(Dispatchers.IO).conflate()

}