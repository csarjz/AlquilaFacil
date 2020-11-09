package com.unprg.alquilafacil.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unprg.alquilafacil.R
import com.unprg.alquilafacil.data.apirestclient.APIUrl
import com.unprg.alquilafacil.domain.model.Anuncio
import com.unprg.alquilafacil.util.OFICINA
import com.unprg.alquilafacil.util.loadImage
import com.unprg.alquilafacil.util.numberFormat
import kotlinx.android.synthetic.main.anuncio_item.view.*
import kotlinx.android.synthetic.main.anuncio_item.view.imagenAnuncio
import kotlinx.android.synthetic.main.anuncio_item.view.precioAnuncio
import kotlinx.android.synthetic.main.anuncio_item.view.tituloAnuncio
import kotlinx.android.synthetic.main.mis_anuncios_item.view.*

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
        if (anuncio.images.isNotEmpty()) {
            holder.itemView.imagenAnuncio.loadImage(APIUrl.IMAGE_ANUNCIO_URL + anuncio.images[0])
        }
        holder.itemView.precioAnuncio.text = "S/ ${numberFormat(anuncio.precio)}"
        holder.itemView.tituloAnuncio.text = anuncio.titulo
        if (anuncio.idcategoria == OFICINA) {
            holder.itemView.detalleTextView.text = "${anuncio.banios} Bñ. - ${numberFormat(anuncio.area)} m2"
        } else {
            holder.itemView.detalleTextView.text = "${anuncio.habitaciones} Hab. - ${anuncio.banios} Bñ. - ${numberFormat(anuncio.area)} m2"
        }
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