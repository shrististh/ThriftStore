package com.example.thriftstore.api

import com.example.retrofitstarterbatch26.ui.model.User
importcom.example.retrofitstarterbatch26.ui.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class userApi {

    // user api
    @POST("auth/register")
    suspend fun registerUser(
        @Body user: User
    ): Response<UserResponse>

    //Invoke
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<UserResponse>

}