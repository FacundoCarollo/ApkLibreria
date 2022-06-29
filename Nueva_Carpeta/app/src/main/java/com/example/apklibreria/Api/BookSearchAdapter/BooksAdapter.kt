package com.example.apklibreria.Api.BookSearchAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.apklibreria.Api.ModelBooks.BooksResultado



import com.example.apklibreria.databinding.ActivityBookItemsBinding

class BooksAdapter(private val datos: ArrayList<BooksResultado>,
                   private val onClick: (BooksResultado) -> Unit
) : RecyclerView.Adapter<BooksAdapter.BookSearchResultViewHolder>() {

    inner class BookSearchResultViewHolder(private val item: ActivityBookItemsBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bindData(bookResultadosBusqueda: BooksResultado) {

            item.txtTitulo.text = bookResultadosBusqueda.title
            item.txtpublisher.text = bookResultadosBusqueda.publisher
            item.smallThumbnail.load(bookResultadosBusqueda.bookSmallThumbnail)
            item.searchResult.apply {
                setOnClickListener {
                    onClick(bookResultadosBusqueda)
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchResultViewHolder {
        return BookSearchResultViewHolder(
            ActivityBookItemsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BookSearchResultViewHolder, position: Int) {
        holder.bindData(datos[position])
    }

    override fun getItemCount(): Int {
        return datos.size
    }
}