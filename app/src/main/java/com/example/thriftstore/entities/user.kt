package com.example.thriftstore.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class user(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,
    var fname: String? = null,
    var lname: String? = null,
    var username: String? = null,
    var password: String? = null
)