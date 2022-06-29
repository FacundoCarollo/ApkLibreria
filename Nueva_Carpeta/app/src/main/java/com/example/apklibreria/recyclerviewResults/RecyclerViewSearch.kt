package com.example.apklibreria.recyclerviewResults

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.apklibreria.Api.BookSearchAdapter.BooksAdapter
import com.example.apklibreria.Api.ModelBooks.BooksResultado
import com.example.apklibreria.Detalles.Detalle
import com.example.apklibreria.Inicio.Inicio
import com.example.apklibreria.databinding.ActivityRecyclerViewSearchBinding

class RecyclerViewSearch : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewSearchBinding
    private var posicion: Int = 1
    private lateinit var dni: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Libros"

        val id: ArrayList<String> =
            intent.getStringArrayListExtra("id") as ArrayList<String>
        val bookName: ArrayList<String> =
            intent.getStringArrayListExtra("bookName") as ArrayList<String>
        val bookPublisher: ArrayList<String> =
            intent.getStringArrayListExtra("publisher") as ArrayList<String>
        val bookSmallThumbnail: ArrayList<String> =
            intent.getStringArrayListExtra("bookSmallThumbnail") as ArrayList<String>
        val bookThumbnail: ArrayList<String> =
            intent.getStringArrayListExtra("bookThumbnail") as ArrayList<String>
        val bookDescription: ArrayList<String> =
            intent.getStringArrayListExtra("bookDescription") as ArrayList<String>
            dni = intent.getStringExtra("dni") as String



        val datos = arrayListOf<BooksResultado>()
        repeat(bookName.size - 1) {
            datos.add(
                BooksResultado(
                    id[posicion],
                    bookSmallThumbnail[posicion],
                    bookName[posicion],
                    bookPublisher[posicion],
                    bookDescription[posicion],
                    bookThumbnail[posicion],
                    dni
                )
            )
            posicion += 1
        }

        val bookResultadoAdaptador = BooksAdapter(datos) {
            val intent = Intent(this, Detalle::class.java)
            intent.putExtra("id", it.id)
            intent.putExtra("bookName", it.title)
            intent.putExtra("bookPublisher", it.publisher)
            intent.putExtra("bookDescription", it.bookDescription)
            intent.putExtra("bookThumbnail", it.bookThumbnail)
            intent.putExtra("dni", it.dni)
            startActivity(intent)
        }
        binding.resultado.adapter = bookResultadoAdaptador
        binding.resultado.layoutManager = LinearLayoutManager(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, Inicio::class.java)
        intent.putExtra("dni", dni)

        startActivity(intent)


    }
}