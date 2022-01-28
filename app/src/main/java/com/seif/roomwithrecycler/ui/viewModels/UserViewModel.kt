package com.seif.roomwithrecycler.ui.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seif.roomwithrecycler.model.entity.User
import com.seif.roomwithrecycler.model.repository.LocalRepositoryImplementation
import com.seif.roomwithrecycler.model.local.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(context: Context) : ViewModel() {
    private var localRepositoryImplementation: LocalRepositoryImplementation
    private var userMutableLiveData = MutableLiveData<List<User>>()
    val userLiveData:LiveData<List<User>> get() = userMutableLiveData // when value assigned to mutable live data then userLiveData take this value from it
    init {
         val db = UserDatabase.getInstance(context)
         localRepositoryImplementation = LocalRepositoryImplementation(db)
    }

    fun addUser(user: User){
        viewModelScope.launch (Dispatchers.IO) { // launch -> used when I don't need function to return thing
            localRepositoryImplementation.addUser(user)
        }
    }

    fun getAllUsers(){
            viewModelScope.launch (Dispatchers.IO) {
                userMutableLiveData.postValue(localRepositoryImplementation.getData())
            }
    }

    fun deleteUser(user:User){
        viewModelScope.launch {
            localRepositoryImplementation.deleteUser(user)
        }
    }
}