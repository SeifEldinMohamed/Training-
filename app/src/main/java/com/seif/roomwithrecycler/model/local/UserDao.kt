package com.seif.roomwithrecycler.model.local

import androidx.room.*
import com.seif.roomwithrecycler.model.entity.User

@Dao
interface UserDao {
    @Insert()
    fun addUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("select * from user_table")
    fun getAllUsers():List<User>

    @Query("select * from user_table where email =:userEmail limit 1")
    fun getUserByEmail(userEmail:String):User

    @Query("select * from user_table where email like:userEmail AND password like:userPassword limit 1")
    fun getUserByEmailAndPassword(userEmail:String, userPassword:String):User
}