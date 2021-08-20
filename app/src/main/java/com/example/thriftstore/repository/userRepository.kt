package com.example.thriftstore.repository

import com.example.thriftstore.api.apiRequest
import com.example.thriftstore.response.UserResponse
import com.example.thriftstore.ui.api.MyApiRequest
import com.example.thriftstore.ui.api.ServiceBuilder
import com.example.thriftstore.ui.api.UserAPI
import com.example.thriftstore.ui.model.User
import com.example.thriftstore.ui.response.UserResponse

class userRepository : MyApiRequest() {

    private val userApi =
        ServiceBuilder.buildService(UserAPI::class.java)

    //Register user
    suspend fun registerUser(user: User): UserResponse {
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