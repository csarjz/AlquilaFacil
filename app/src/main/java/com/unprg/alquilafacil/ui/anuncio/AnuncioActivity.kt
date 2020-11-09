package com.unprg.alquilafacil.ui.anuncio

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.unprg.alquilafacil.R
import com.unprg.alquilafacil.data.apirestclient.APIUrl.BASE_IMAGE_URL
import com.unprg.alquilafacil.data.usecase.AnuncioUseCase
import com.unprg.alquilafacil.domain.model.Anuncio
import com.unprg.alquilafacil.domain.model.Person
import com.unprg.alquilafacil.ui.chat.ChatActivity
import com.unprg.alquilafacil.util.OFICINA
import com.unprg.alquilafacil.util.ResponseType
import com.unprg.alquilafacil.util.loadAnuncioImage
import com.unprg.alquilafacil.util.numberFormat
import kotlinx.android.synthetic.main.activity_anuncio.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnuncioActivity : AppCompatActivity() {
    private var idanuncio = 0
    private lateinit var anuncio: Anuncio
    private var idCurrentPerson = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anuncio)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        idanuncio = intent.getIntExtra("idanuncio", 0)
        if(idanuncio <= 0) {
            finish()
            return
        }
        idCurrentPerson = Person.getLocal(this).idpersona

        callServiceListAnuncios()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }

    private fun setupView() {
        containerAnuncio.visibility = View.VISIBLE
        val imagenName = if(anuncio.images.isNotEmpty()) anuncio.images[0] else ""
        imagenAnuncio.loadAnuncioImage(imagenName)
        precioAnuncio.text = "S/ ${numberFormat(anuncio.precio)}"
        tituloAnuncio.text = anuncio.titulo
        if (anuncio.idcategoria == OFICINA) {
            detalleTextView.text = "${anuncio.banios} Bñ. - ${numberFormat(anuncio.area)} m2"
            rowHabitaciones.visibility = View.GONE
            rowAmoblado.visibility = View.GONE
        } else {
            detalleTextView.text = "${anuncio.habitaciones} Hab. - ${anuncio.banios} Bñ. - ${numberFormat(anuncio.area)} m2"
        }
        tipo.text = anuncio.categoryName
        area.text = "${numberFormat(anuncio.area)} m2"
        numBanios.text = anuncio.banios.toString()
        numHabitaciones.text = anuncio.habitaciones.toString()
        amoblado.text = if(anuncio.amueblado==1) "SI" else "NO"
        direccion.text = anuncio.direccion
        descripcion.text = anuncio.descripcion

        if (anuncio.idpersona == idCurrentPerson) {
            actionsLayout.visibility = View.GONE
        } else {
            btnLlamar.setOnClickListener {
                if (anuncio.telefono.isNotBlank()) {
                    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${anuncio.telefono}")))
                } else if (anuncio.telefonoPersona.isNotBlank()) {
                    startActivity(
                        Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:${anuncio.telefonoPersona}")
                        )
                    )
                } else {
                    Toast.makeText(
                        this,
                        "El dueño no ha proporcionado un número de teléfono",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            btnChat.setOnClickListener {
                startActivity(Intent(this, ChatActivity::class.java).apply {
                    putExtra("anuncio", anuncio)
                })
            }
        }
    }

    private fun callServiceListAnuncios() {

        GlobalScope.launch (Dispatchers.Main) {

            val response = withContext(Dispatchers.IO) {
                AnuncioUseCase(this@AnuncioActivity).getAnuncio(idanuncio)
            }

            when (response) {
                is ResponseType.Success -> {
                    anuncio = response.value
                    setupView()
                }
                is ResponseType.Error -> {
                    Toast.makeText(this@AnuncioActivity, response.value.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}