package com.seif.roomwithrecycler.model.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.seif.roomwithrecycler.model.entity.Message
import com.seif.roomwithrecycler.model.entity.User

interface LocalRepository {
    suspend fun insertOrUpdateMessage(message: Message)
    suspend fun addMessage(message:Message)
    suspend fun deleteMessage(message:Message)
    suspend fun getMessages():List<Message>

    suspend fun addUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun getAllUsers():List<User>
    suspend fun getUserByEmail(userEmail:String):User
    suspend fun getUserByEmailAndPassword(userEmail:String, userPassword:String):User

}

/** Repository(store) Architecture pattern
 * collect all functions in local database(sqlite(room) and SharedPreference) and put it in repository
 * also we can make repository for local and remote database

 * The repository pattern is an abstraction used to hide the multiple data sources
 * we may have in our application, data in an application may come from an internal database,
 * or, an external service such as a Web API.
 * This pattern is adopted and widely used when developing Android applications
 *
  */

