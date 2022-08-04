package com.seif.roomwithrecycler.model.local

import androidx.room.*
import com.seif.roomwithrecycler.model.entity.Message

@Dao
interface MessageDao {
    // if we have conflict (trying to add a user and we found that he is already in the database)
    // then replace this data (update)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateMessage(message:Message)
    @Insert
    fun addMessage(message:Message)
    @Delete
    fun deleteMessage(message:Message)
    @Query("select * from message_table")
    fun getMessages():List<Message>
}