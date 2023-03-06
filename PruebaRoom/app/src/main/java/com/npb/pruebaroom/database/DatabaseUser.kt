package com.npb.pruebaroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.npb.pruebaroom.data.User
import com.npb.pruebaroom.data.UserDao

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class DatabaseUser : RoomDatabase() {
    abstract fun userDao() : UserDao

    //Singleton creado a mano para la conexion a la base de datos
    companion object {
        @Volatile
        private var INSTANCE : DatabaseUser? = null

        fun getDatabse(context : Context): DatabaseUser {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseUser::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}