package com.seif.roomwithrecycler.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seif.roomwithrecycler.model.entity.Message
import com.seif.roomwithrecycler.model.repository.LocalRepositoryImplementation
import com.seif.roomwithrecycler.model.local.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MessageViewModel(context: Context) : ViewModel() {
    private var localRepositoryImplementation: LocalRepositoryImplementation

    private var userMutableLiveData = MutableLiveData<List<Message>>()
    val messageLiveData: LiveData<List<Message>> get() = userMutableLiveData // when value assigned to mutable live data then userLiveData take this value from it

    init {
        val db = UserDatabase.getInstance(context)
        localRepositoryImplementation = LocalRepositoryImplementation(db)
    }

    fun addUser(message: Message) {
        viewModelScope.launch(Dispatchers.IO) { // launch -> used when I don't need function to return thing
            localRepositoryImplementation.addMessage(message)
        }
    }

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userMutableLiveData.postValue(localRepositoryImplementation.getMessages())
        }
    }

    fun deleteUser(message: Message) {
        viewModelScope.launch {
            localRepositoryImplementation.deleteMessage(message)
        }
    }
}