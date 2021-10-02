package com.example.mainthriftstoreandroid.api

import com.example.mainthriftstoreandroid.model.user
import com.example.mainthriftstoreandroid.response.userResponse
import okhttp3.MultipartBody
//import okhttp3.Response
import retrofit2.Response
import retrofit2.Callback
import retrofit2.http.*

interface userApi {


    // user api
    @POST("user/register")
    suspend fun registerUser(
        @Body user: user
    ): Response<userResponse>


    //Invoke
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<userResponse>


    //retrive User
    @GET("user/show")
    suspend fun retrieveUser(
        @Header("Authorization") token: String,
    ): Response<userResponse>


    //Update Student
    @PUT("user/update/{id}")
    suspend fun updateUser(
        @Header("Authorization") token: String
    ): Response<userResponse>


    @Multipart
    @PUT("profile/upload/{id}")
    suspend fun uploadImage(

        @Part image: MultipartBody.Part
    ): Response<userResponse>
}




