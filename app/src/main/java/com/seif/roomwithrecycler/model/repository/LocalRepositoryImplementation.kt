package com.seif.roomwithrecycler.model.repository

import com.seif.roomwithrecycler.model.entity.Message
import com.seif.roomwithrecycler.model.entity.User
import com.seif.roomwithrecycler.model.local.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImplementation(private val db: UserDatabase): LocalRepository {
    override suspend fun insertOrUpdateMessage(message: Message) {
        withContext(Dispatchers.IO){
            db.messageDao().insertOrUpdateMessage(message)
        }
    }

    override suspend fun addMessage(message: Message) {
        withContext(Dispatchers.IO){
            db.messageDao().addMessage(message)
        }
    }

    override suspend fun deleteMessage(message: Message) {
        withContext(Dispatchers.IO){
            db.messageDao().deleteMessage(message)
        }
    }

//    override suspend fun getData(): List<User> {
//       return withContext(Dispatchers.IO){
//            db.userDao().getData()
//        }
//    }

       override suspend fun getMessages():List<Message> =
        withContext(Dispatchers.IO) {
            db.messageDao().getMessages()
        }

    // user

    override suspend fun addUser(user: User) {
        withContext(Dispatchers.IO){
            db.userDao().addUser(user)
        }
    }

    override suspend fun deleteUser(user: User) {
        withContext(Dispatchers.IO) {
            db.userDao().deleteUser(user)
        }
    }
    // return list of all users
    override suspend fun getAllUsers() =
        withContext(Dispatchers.IO) {
             db.userDao().getAllUsers()
        }

    // return user
    override suspend fun getUserByEmail(userEmail: String) =
        withContext(Dispatchers.IO){
            db.userDao().getUserByEmail(userEmail)
        }

    override suspend fun getUserByEmailAndPassword(userEmail: String, userPassword: String) =
        withContext(Dispatchers.IO){
            db.userDao().getUserByEmailAndPassword(userEmail, userPassword)
        }

}