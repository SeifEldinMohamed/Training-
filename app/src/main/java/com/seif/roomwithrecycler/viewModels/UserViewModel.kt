package com.seif.roomwithrecycler.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seif.roomwithrecycler.model.entity.User
import com.seif.roomwithrecycler.model.local.UserDatabase
import com.seif.roomwithrecycler.model.repository.LocalRepositoryImplementation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(context: Context):ViewModel() {
    private var localRepositoryImplementation: LocalRepositoryImplementation

    private var userMutableLiveData = MutableLiveData<List<User>>()
    val userLiveData: LiveData<List<User>> get() = userMutableLiveData // when value assigned to mutable live data then userLiveData take this value from it

    private var userFoundMutableLiveData = MutableLiveData<User?>()
    val userFoundLiveData: LiveData<User?> get() = userFoundMutableLiveData // when value assigned to mutable live data then userLiveData take this value from it

    init {
        val db = UserDatabase.getInstance(context)
        localRepositoryImplementation = LocalRepositoryImplementation(db)
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) { // launch -> used when I don't need function to return thing
            localRepositoryImplementation.addUser(user)
        }
    }

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userMutableLiveData.postValue(localRepositoryImplementation.getAllUsers())
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            localRepositoryImplementation.deleteUser(user)
        }
    }

    fun getUserByEmail(email:String) {
        viewModelScope.launch {
            userFoundMutableLiveData.postValue(localRepositoryImplementation.getUserByEmail(email))
        }
    }

    fun getUserByEmailAndPassword(email:String, password:String) {
        viewModelScope.launch {
            userFoundMutableLiveData.postValue(localRepositoryImplementation.getUserByEmailAndPassword(email, password))
        }
    }
}