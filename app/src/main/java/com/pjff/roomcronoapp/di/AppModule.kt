package com.pjff.roomcronoapp.di

import android.content.Context
import androidx.room.Room
import com.pjff.roomcronoapp.room.CronosDatabase
import com.pjff.roomcronoapp.room.CronosDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
  Paso 3.2, tipo Object aquí definimos lo que estaremos inyectando en otras clases.
  el patron de dependencias usa Singleton
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    //Estaremos proveiendo nuestra base de datos
    @Provides
    //En CronosDao, tenemos todas las consultas.
    fun providesCronosDao(cronoDatabase: CronosDatabase) : CronosDatabaseDao {
        return cronoDatabase.cronosDao()
    }

    //Hacemos la instancia de nuestra base de datos
    @Singleton
    @Provides
    fun providesCronosDatabase(@ApplicationContext context : Context): CronosDatabase {
        //Retornamos la base de datos y necesitamos 2 el contexto y la base de datos.
        return Room.databaseBuilder(
            context,
            //Creamos la base de datos y le damos un  nombre de la base de datos.
            CronosDatabase::class.java, "cronos_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

}