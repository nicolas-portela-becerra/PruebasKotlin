package com.npb.pruebaroom.database

import androidx.lifecycle.LiveData
import com.npb.pruebaroom.data.User
import com.npb.pruebaroom.data.UserDao

class Repository(private val userDao : UserDao) {
    val readAllData : LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}