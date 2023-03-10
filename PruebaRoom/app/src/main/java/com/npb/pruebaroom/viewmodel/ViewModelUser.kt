package com.npb.pruebaroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.npb.pruebaroom.core.DatabaseUser
import com.npb.pruebaroom.repository.Repository
import com.npb.pruebaroom.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelUser(application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<User>>
    private val repository : Repository
    init {
        val userDao = DatabaseUser.getDatabse(application).userDao()
        repository = Repository(userDao)
        readAllData = repository.readAllData
    }
    fun addUser(user : User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
    fun updateUser(user : User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user : User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }
}