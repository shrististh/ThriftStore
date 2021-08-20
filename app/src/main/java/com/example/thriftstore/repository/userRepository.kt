package com.example.thriftstore.repository

import com.example.thriftstore.api.apiRequest
import com.example.thriftstore.api.serviceBuilder
import com.example.thriftstore.entities.user
import com.example.thriftstore.response.UserResponse


class userRepository : MyApiRequest() {

    private val userApi =
        serviceBuilder.serviceBuilder(userApi::class.java)

    //Register user
    suspend fun registerUser(user: user): UserResponse {
        return apiRequest {
            userApi.registerUser(user)
        }
    }

    // Login user
    suspend fun login(username: String, password: String): UserResponse {
        return apiRequest {
            userApi.login(username, password)
        }
    }
}

open class MyApiRequest {

}
