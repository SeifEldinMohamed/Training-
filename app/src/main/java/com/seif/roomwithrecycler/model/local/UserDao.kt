package com.seif.roomwithrecycler.model.local

import androidx.room.*
import com.seif.roomwithrecycler.model.entity.User

@Dao
interface UserDao {
    // if we have conflict (trying to add a user and we found that he is already in the database)
    // then replace this data (update)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertOrUpdateUser(user:User)
    @Insert
   suspend fun addUser(user:User)
    @Delete
   suspend fun deleteUser(user:User)
    @Query("select * from user_table")
   suspend fun getData():List<User>
}