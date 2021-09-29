package com.example.thriftstore.model

data class user (
    val _id: String? = " ",
    val email : String="",
    val location: String?,
    val password: String? = null,
)