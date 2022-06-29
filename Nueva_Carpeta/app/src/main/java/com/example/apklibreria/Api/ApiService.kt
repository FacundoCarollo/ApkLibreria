package com.example.apklibreria.Api
import com.example.apklibreria.Api.ModelBooks.Books

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(" ")
    suspend fun getBooks(
        @Query("q") Title: String,
        @Query("key") apiKey: String
    ): Response<Books>
}