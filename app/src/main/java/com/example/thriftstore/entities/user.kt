package com.example.thriftstore.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class user(
    val _id: String? = "",
//    @PrimaryKey(autoGenerate = true)
    var email: String? = null,
    var password: String? = null,
    var confirmpassword: String? = null,
    var location: String? = null
)