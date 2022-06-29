package com.example.apklibreria.Detalles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.example.apklibreria.Inicio.Inicio
import com.example.apklibreria.databinding.ActivityDetalleBinding


class Detalle : AppCompatActivity() {
    private lateinit var dni : String
    private lateinit var binding: ActivityDetalleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detalle"


        val bookThumbnail: String = intent.getStringExtra("bookThumbnail").toString()
        val bookName: String = intent.getStringExtra("bookName").toString()
        val bookPublisher: String = intent.getStringExtra("bookPublisher").toString()
        val bookDescription: String = intent.getStringExtra("bookDescription").toString()
        dni = intent.getStringExtra("dni").toString()

        binding.bookName.text = bookName
        binding.publisherNameTextView.text = bookPublisher
        binding.bookDescription.text = bookDescription
        binding.bookThumbnail.load(bookThumbnail)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, Inicio::class.java)
        intent.putExtra("dni", dni)
        startActivity(intent)


    }

}