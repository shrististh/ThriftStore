package com.example.mainthriftstoreandroid.repository

import android.util.Log
import com.example.mainthriftstoreandroid.api.apiRequest
import com.example.mainthriftstoreandroid.api.serviceBuilder
import com.example.mainthriftstoreandroid.model.user
import com.example.mainthriftstoreandroid.response.userResponse
import okhttp3.MultipartBody

class userRepository : apiRequest() {

    private val userApi =
        serviceBuilder.buildService(com.example.mainthriftstoreandroid.api.userApi::class.java)


    //        Register user
    suspend fun registerUser(data: user): userResponse {
        return apiRequest {
            userApi.registerUser(data)
        }
    }

    //         Login user
    suspend fun login(email: String, password: String): userResponse {

        var response =  apiRequest {

            userApi.login(email, password)

        }
        println(response)
        return response
    }

    suspend fun uploadImage(body : MultipartBody.Part ): userResponse {
        return apiRequest{
            userApi.uploadImage(body)
        }
    }

}