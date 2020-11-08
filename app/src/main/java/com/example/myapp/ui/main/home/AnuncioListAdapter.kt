package com.example.myapp.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.domain.model.Anuncio
import kotlinx.android.synthetic.main.anuncio_item.view.*

class AnuncioListAdapter(
    private var listAnuncios: List<Anuncio>,
    private val listener: MyViewHolder.OnAdapterListener
) : RecyclerView.Adapter<AnuncioListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anuncio_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listAnuncios.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val anuncio = this.listAnuncios[position]
        holder.itemView.precioAnuncio.text = "S/ ${anuncio.precio}"
        holder.itemView.tituloAnuncio.text = anuncio.titulo
        holder.itemView.setOnClickListener {listener.onItemClickListener(anuncio, holder.itemView)}
    }

    fun updateList(anuncioList: List<Anuncio>) {
        this.listAnuncios = anuncioList
        this.notifyDataSetChanged()
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        interface OnAdapterListener {
            fun onItemClickListener(anuncio: Anuncio, view: View)
        }
    }
}