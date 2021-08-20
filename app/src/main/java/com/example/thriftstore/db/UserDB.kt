package com.example.thriftstore.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thriftstore.Dao.UserDAO
import com.example.thriftstore.entities.user


@Database(
    entities = [(user::class)],
    version = 2,
    exportSchema = false
)


abstract class UserDB : RoomDatabase() {
    abstract fun getUserDAO():UserDAO

    companion object {
        @Volatile
        private var instance: UserDB? = null

        fun getInstance(context: Context): UserDB {
            if (instance == null) {
                synchronized(UserDB::class) {
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDB::class.java,
                "StudentDB"
            ).build()
    }
}