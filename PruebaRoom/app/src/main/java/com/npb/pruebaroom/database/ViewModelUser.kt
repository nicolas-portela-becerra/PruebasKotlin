package com.npb.pruebaroom.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.npb.pruebaroom.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ViewModelUser(application: Application) : AndroidViewModel(application) {
    private val readAllData : LiveData<List<User>>
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
}