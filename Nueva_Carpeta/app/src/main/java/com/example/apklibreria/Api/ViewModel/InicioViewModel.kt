package com.example.apklibreria.Api.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.example.apklibreria.Api.ModelBooks.Books
import com.example.apklibreria.Api.Repositorio.Repositorio
import kotlinx.coroutines.launch
import retrofit2.Response



class InicioViewModel(private val repositorio: Repositorio) : ViewModel() {

    val respuesta: MutableLiveData<Response<Books>> = MutableLiveData()

    fun getBooks(title: String, apiKey: String) {
        viewModelScope.launch {
            val respuestas: Response<Books> = repositorio.getBooks(title, apiKey)
            respuesta.value = respuestas
        }
    }
}