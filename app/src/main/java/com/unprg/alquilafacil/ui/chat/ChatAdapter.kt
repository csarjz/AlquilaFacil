package com.unprg.alquilafacil.ui.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unprg.alquilafacil.R
import com.unprg.alquilafacil.domain.model.Chat
import kotlinx.android.synthetic.main.chat_item.view.*

class ChatAdapter(
    private var mensajes: List<Chat>, val idCurrentPerson: Int, val context: Context
) : RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mensajes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val chat = this.mensajes[position]

        if (chat.idpersona == idCurrentPerson) {
            holder.itemView.chatTextLeft.text = ""
            holder.itemView.chatTextRight.text = chat.mensaje
            holder.itemView.chatCardLeft.visibility = View.GONE
            holder.itemView.chatCardRight.visibility = View.VISIBLE
        } else {
            holder.itemView.chatTextLeft.text = chat.mensaje
            holder.itemView.chatTextRight.text = ""
            holder.itemView.chatCardLeft.visibility = View.VISIBLE
            holder.itemView.chatCardRight.visibility = View.GONE
        }
    }

    fun updateList(newMenssages: List<Chat>) {
        this.mensajes = newMenssages
        this.notifyDataSetChanged()
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}