package com.example.myandroidapplication.api

import com.example.myandroidapplication.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface ProductApi {
    @GET("product/show")
    suspend fun getproduct(
        @Header("Authorization") token : String
    ): Response<ProductResponse>
}