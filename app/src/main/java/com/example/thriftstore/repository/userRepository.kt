package com.example.thriftstore.repository

import com.example.retrofitstarterbatch26.ui.api.MyApiRequest
import com.example.retrofitstarterbatch26.ui.api.ServiceBuilder
import com.example.retrofitstarterbatch26.ui.api.UserAPI
import com.example.retrofitstarterbatch26.ui.model.User
import com.example.retrofitstarterbatch26.ui.response.UserResponse

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