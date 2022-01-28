package com.seif.roomwithrecycler.model.repository

import com.seif.roomwithrecycler.model.entity.User
import com.seif.roomwithrecycler.model.local.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImplementation(private val db: UserDatabase): LocalRepository {
    override suspend fun insertOrUpdateUser(user: User) {
        withContext(Dispatchers.IO){
            db.userDao().insertOrUpdateUser(user)
        }
    }

    override suspend fun addUser(user: User) {
        withContext(Dispatchers.IO){
            db.userDao().addUser(user)
        }
    }

    override suspend fun deleteUser(user: User) {
        withContext(Dispatchers.IO){
            db.userDao().deleteUser(user)
        }
    }

//    override suspend fun getData(): List<User> {
//       return withContext(Dispatchers.IO){
//            db.userDao().getData()
//        }
//    }

       override suspend fun getData():List<User> =
        withContext(Dispatchers.IO) {
            db.userDao().getData()
        }

}