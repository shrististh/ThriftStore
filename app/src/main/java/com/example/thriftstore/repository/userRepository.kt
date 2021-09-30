package com.example.thriftstore.repository

import com.example.thriftstore.api.apiRequest
import com.example.thriftstore.api.serviceBuilder
import com.example.thriftstore.entities.user
import com.example.thriftstore.response.UserResponse
import okhttp3.MultipartBody




    class userRepository : apiRequest() {

        private val userApi =
            serviceBuilder.buildService(com.example.thriftstore.api.userApi::class.java)


//        Register user
        suspend fun registerUser(data: user): UserResponse {
            return apiRequest {
                userApi.registerUser(data)
            }
        }

//         Login user
        suspend fun login(username: String, password: String): UserResponse {
            return apiRequest {
                userApi.login(username, password)
            }
        }

        suspend fun uploadImage(body : MultipartBody.Part ): UserResponse{
            return apiRequest{
                userApi.uploadImage(body)
            }
        }


    }









