package com.npb.pruebaroom.repository

import androidx.lifecycle.LiveData
import com.npb.pruebaroom.model.User
import com.npb.pruebaroom.model.UserDao

class Repository(private val userDao : UserDao) {
    val readAllData : LiveData<List<User>> = userDao.readAllData()
    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user : User) {
        userDao.updateUser(user)
    }
}