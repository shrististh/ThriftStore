package com.example.thriftstore.Dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.thriftstore.entities.user

@Dao
interface UserDAO {
    @Insert
    suspend fun registerUser(user: user)

    @Query("select * from user where username=(:username) and password=(:password)")
    suspend fun checkUser(username: String, password: String): user
}