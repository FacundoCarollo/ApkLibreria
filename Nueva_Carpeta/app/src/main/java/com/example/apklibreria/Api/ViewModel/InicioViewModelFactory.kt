package com.example.apklibreria.Api.ViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apklibreria.Api.Repositorio.Repositorio


class InicioViewModelFactory(private val repositorio: Repositorio) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InicioViewModel(repositorio) as T
    }

}

