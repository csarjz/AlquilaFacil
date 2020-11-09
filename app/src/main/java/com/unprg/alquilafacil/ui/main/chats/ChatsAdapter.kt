package com.unprg.alquilafacil.ui.main.chats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unprg.alquilafacil.R
import com.unprg.alquilafacil.data.apirestclient.APIUrl
import com.unprg.alquilafacil.domain.model.Anuncio
import com.unprg.alquilafacil.util.loadAnuncioImage
import com.unprg.alquilafacil.util.loadPersonImage
import com.unprg.alquilafacil.util.numberFormat
import kotlinx.android.synthetic.main.activity_anuncio.*
import kotlinx.android.synthetic.main.mis_anuncios_item.view.*

class ChatsAdapter(
    private var listAnuncios: List<Anuncio>,
    private val idcurrentpersona: Int,
    private val listener: MyViewHolder.OnAdapterListener
) : RecyclerView.Adapter<ChatsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mis_anuncios_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listAnuncios.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val anuncio = this.listAnuncios[position]
        if (anuncio.idpersona == idcurrentpersona) {
            holder.itemView.precioAnuncio.text = anuncio.chat.nombrePersona
            holder.itemView.imagenAnuncio.loadPersonImage("${anuncio.chat.idpersona}.jpg")
        } else {
            holder.itemView.precioAnuncio.text = anuncio.nombrePersona
            holder.itemView.imagenAnuncio.loadPersonImage("${anuncio.idpersona}.jpg")
        }
        holder.itemView.tituloAnuncio.text = anuncio.titulo+" - S/ "+ numberFormat(anuncio.precio)
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