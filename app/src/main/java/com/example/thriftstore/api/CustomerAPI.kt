package com.example.myandroidapplication.api

import com.example.myandroidapplication.model.Customer

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface CustomerAPI {
    @GET("customer/")
    suspend fun getAllCustomers(
        @Header("Authorization") token: String
    ): Response<CustomerGetResponse>

    //
    @DELETE("customer/{id}")
    suspend fun deleteCustomer(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DeleteCustomerResponse>

    @POST("customer/")
    suspend fun insertCustomer(
        @Header("Authorization") token: String,
        @Body customer: Customer
    ): Response<AddCustomerResponse>

    @Multipart
    @PUT("customer/{id}/photo")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ): Response<DeleteCustomerResponse>


}

class CustomerGetResponse {

}

class DeleteCustomerResponse {

}

class AddCustomerResponse {

}
