package com.seif.roomwithrecycler.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.seif.roomwithrecycler.model.entity.User

private const val DATABASE_NAME = "user_table"

@Database(entities = [User::class], version = 1, exportSchema = false)
// export schema(by default equal true) make more than one version for database in history
abstract class UserDatabase : RoomDatabase() {
    // to access the data in database we need Dao
    abstract fun userDao(): UserDao

    // we need to use singleton design pattern
    companion object {

        @Volatile // this instance will appear for all threads in our app that returns it every time (not cashed, outOfDate)
        private var instance: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase {
//          return instance?: synchronized(Any()){ // synchronized made block to the code until assign value to instance
//              instance?: buildDataBase(context).also { instance = it }
//          }
            return if(instance!=null){
                instance!!
            } else{ // instance is equal to null (first time)
                buildDataBase(context).also { instance = it } // it -> holds what returns from function
            }
        }

        private fun buildDataBase(context: Context): UserDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}