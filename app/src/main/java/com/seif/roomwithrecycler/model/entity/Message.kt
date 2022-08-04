package com.seif.roomwithrecycler.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_table") // used to define this class as a entity(table) in our database
data class Message(
    @PrimaryKey(autoGenerate = true) // primary key will be auto generated ex: 0,1,2,3,...
    var id:Int,
    @ColumnInfo(name = "user_name") // used to put column name
    var userName:String,
    @ColumnInfo(name = "user_message")
    var message:String,
    var email:String
                )
