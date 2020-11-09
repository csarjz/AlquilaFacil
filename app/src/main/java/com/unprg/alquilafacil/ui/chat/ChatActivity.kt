package com.unprg.alquilafacil.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.unprg.alquilafacil.R
import com.unprg.alquilafacil.data.usecase.ChatUseCase
import com.unprg.alquilafacil.domain.model.Anuncio
import com.unprg.alquilafacil.domain.model.Message
import com.unprg.alquilafacil.domain.model.Person
import com.unprg.alquilafacil.util.ResponseType
import com.unprg.alquilafacil.util.numberFormat
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.coroutines.*

class ChatActivity : AppCompatActivity() {
    private lateinit var anuncio: Anuncio
    private var idCurrentPerson = 0
    private var mensajes = ArrayList<Message>()
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tempAnuncio: Anuncio? = intent.extras?.getParcelable("anuncio")

        if(tempAnuncio != null) {
            anuncio = tempAnuncio
            idCurrentPerson = Person.getLocal(this).idpersona
            adapter = ChatAdapter(mensajes, idCurrentPerson, this)

            if (anuncio.idpersona == idCurrentPerson) {
                supportActionBar?.title = anuncio.chat.nombrePersona
            } else {
                supportActionBar?.title = anuncio.nombrePersona
            }
        } else {
            finish()
            return
        }

        callServiceListMensajes()
        setupView()
        checkNewMessages()
    }

    private fun setupView() {
        detalleAnuncio.text = anuncio.titulo + "  -  S/ " + numberFormat(anuncio.precio)

        val linearLayoutManager = LinearLayoutManager(this)
        chatRecyclerView.layoutManager = linearLayoutManager
        chatRecyclerView.adapter = adapter

        btnSend.setOnClickListener {
            sentMessage()
        }
    }

    private fun sentMessage() {
        val msj = edtMsj.text.toString().trim()
        if (msj.isNotBlank()) {
            val chat = Message(idchat = anuncio.chat.idchat, idpersona = idCurrentPerson, idanuncio = anuncio.idanuncio, mensaje = msj)
            mensajes.add(chat)
            adapter.updateList(mensajes)
            edtMsj.setText("")
            chatRecyclerView.smoothScrollToPosition(mensajes.size-1)

            callServiceSaveMensaje(chat)
        }
    }

    private fun callServiceSaveMensaje(message: Message) {
        GlobalScope.launch (Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                ChatUseCase(this@ChatActivity).saveMensaje(message)
            }

            when (response) {
                is ResponseType.Success -> {
                    if (anuncio.chat.idchat <= 0 && response.value > 0) {
                        anuncio.chat.idchat = response.value
                    }
                }
                is ResponseType.Error -> {
                    Toast.makeText(this@ChatActivity, response.value.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun callServiceListMensajes() {
        GlobalScope.launch (Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                ChatUseCase(this@ChatActivity).listMensajes(anuncio.chat.idchat, mensajes.size)
            }

            when (response) {
                is ResponseType.Success -> {
                    if (response.value.isNotEmpty()) {
                        mensajes = ArrayList(response.value)
                        adapter.updateList(response.value)
                        chatRecyclerView.smoothScrollToPosition(mensajes.size-1)
                    }
                }
                is ResponseType.Error -> {
                    Toast.makeText(this@ChatActivity, response.value.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun checkNewMessages() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                while (true) {
                    Thread.sleep(3000)
                    withContext(Dispatchers.Main) {
                        callServiceListMensajes()
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }
}