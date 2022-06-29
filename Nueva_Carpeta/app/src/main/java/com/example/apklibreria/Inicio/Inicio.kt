package com.example.apklibreria.Inicio

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.apklibreria.Api.ViewModel.InicioViewModel
import com.example.apklibreria.Api.ConstatesApi.Companion.API_KEY
import com.example.apklibreria.Api.Repositorio.Repositorio

import com.example.apklibreria.Api.ViewModel.InicioViewModelFactory
import com.example.apklibreria.R
import com.example.apklibreria.Usuario.Gestion
import com.example.apklibreria.Usuario.Login
import com.example.apklibreria.Usuario.MainActivity
import com.example.apklibreria.recyclerviewResults.RecyclerViewSearch
import com.example.apklibreria.databinding.ActivityInicioBinding
import java.util.*
import kotlin.collections.ArrayList


class Inicio : AppCompatActivity() {

    private lateinit var binding: ActivityInicioBinding
    private lateinit var viewModel: InicioViewModel
    private lateinit var dni : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Inicio"


        val id = ArrayList<String>()
        val bookName = ArrayList<String>()
        val bookPublisher = ArrayList<String>()
        val bookSmallThumbnail = ArrayList<String>()

        val bookThumbnail = ArrayList<String>()
        val bookDescription = ArrayList<String>()
        val repositorio = Repositorio()
        val viewModelFactory = InicioViewModelFactory(repositorio)

        dni =   intent.getStringExtra("dni").toString()

        viewModel = ViewModelProvider(this, viewModelFactory).get(InicioViewModel::class.java)
        binding.searchButton.setOnClickListener {
            if (VerificarInternet(this)) {
                val title: String = binding.search.text.toString()
                if (title.isNotEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        "Buscando \"${
                            title.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                            }
                        }",
                        Toast.LENGTH_LONG
                    ).show()

                    viewModel.getBooks(title, API_KEY)
                    viewModel.respuesta.observe(this, ({ respuesta ->
                        if (respuesta.isSuccessful) {
                            respuesta.body()!!.items.forEach {
                                id.add(it.id.toString())
                                bookName.add(it.volumeInfo!!.title.toString())
                                bookPublisher.add(it.volumeInfo!!.publisher.toString())
                                bookSmallThumbnail.add(it.volumeInfo!!.imageLinks!!.smallThumbnail.toString())
                                bookThumbnail.add(it.volumeInfo!!.imageLinks!!.thumbnail.toString())
                                bookDescription.add(it.volumeInfo!!.description.toString())
                                dni
                            }
                            if(bookName.isNotEmpty()) {
                                val intent = Intent(this, RecyclerViewSearch::class.java)
                                intent.putExtra("id", id)
                                intent.putExtra("bookName", bookName)
                                intent.putExtra("publisher", bookPublisher)
                                intent.putExtra("bookSmallThumbnail", bookSmallThumbnail)
                                intent.putExtra("bookThumbnail", bookThumbnail)
                                intent.putExtra("bookDescription", bookDescription)
                                intent.putExtra("dni",dni)

                                startActivity(intent)
                                finish()

                            }
                        }
                    }))
                } else {
                    Toast.makeText(applicationContext, "Ingrese un Titulo", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(applicationContext, "No hay conexion a internet", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


       val intentGestion = Intent(this, Gestion::class.java)

        intentGestion.putExtra("dni", dni)

        val intentInicio = Intent(this, Inicio::class.java)
        intentInicio.putExtra("dni", dni)


        when(item.itemId){
            R.id.inicio -> startActivity(intentInicio)
            R.id.usuarios -> startActivity(intentGestion)
            R.id.salir -> startActivity(Intent(this,MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun VerificarInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            val red = connectivityManager.activeNetwork ?: return false


            val redActivada = connectivityManager.getNetworkCapabilities(red) ?: return false

            return when {

                redActivada.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true


                redActivada.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true


                else -> false
            }
        } else {

            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}