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

//Vid 118, tipo Object ,definimos lo que estaremos inyectando en otras clases.
//el patron de dependencias usa Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    //En CronosDao, tenemos todas las consultas.
    fun providesCronosDao(cronoDatabase: CronosDatabase) : CronosDatabaseDao {
        return cronoDatabase.cronosDao()
    }

    //hacemos la instancia de nuestra base de datos
    @Singleton
    @Provides
    fun providesCronosDatabase(@ApplicationContext context : Context): CronosDatabase {
        //retornamos la base de datos y nos permite 2 cosas
        return Room.databaseBuilder(
            context,
            //Creamos la base de datos y le damos un  nombre.
            CronosDatabase::class.java, "cronos_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

}