package com.example.pruebaroom.data.inyecciondependencias

import android.content.Context
import androidx.room.Room
import com.example.pruebaroom.data.database.ModeloDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, ModeloDatabase::class.java, "BdModelo")

    @Singleton
    @Provides
    fun provideModeloDao(db:ModeloDatabase) = db.getModeloDao()
}