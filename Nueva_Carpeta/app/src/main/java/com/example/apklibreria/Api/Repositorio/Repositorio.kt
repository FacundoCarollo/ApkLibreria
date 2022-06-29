package com.example.apklibreria.Api.Repositorio

import com.example.apklibreria.Api.ModelBooks.Books
import com.example.apklibreria.Api.Retrofit
import retrofit2.Response

class Repositorio {
    suspend fun getBooks(title: String, apiKey: String): Response<Books> {
        return Retrofit.api.getBooks(title, apiKey)
    }
}

