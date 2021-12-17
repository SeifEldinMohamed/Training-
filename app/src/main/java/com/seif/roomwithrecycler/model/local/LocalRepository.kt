package com.seif.roomwithrecycler.model.local

import com.seif.roomwithrecycler.model.entity.User

interface LocalRepository {
    suspend fun insertOrUpdateUser(user: User)
    suspend fun addUser(user:User)
    suspend fun deleteUser(user:User)
    suspend fun getData():List<User>
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

