package com.example.myapp.ui.main.publish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapp.R
import com.example.myapp.data.usecase.AnuncioUseCase
import com.example.myapp.domain.model.Anuncio
import com.example.myapp.util.ResponseType
import kotlinx.android.synthetic.main.activity_publish.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class PublishActivity : AppCompatActivity() {
    private var idcategoria = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        idcategoria = intent.getIntExtra("idcategoria", 0)
        if(idcategoria <= 0) {
            finish()
            return
        }
        btn_publicar.setOnClickListener{ publicar() }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }

    private fun publicar() {
        try {
            if (precio_edt.text.toString().isBlank() || precio_edt.text.toString().toFloat() <= 0) {
                Toast.makeText(this, "Precio no válido", Toast.LENGTH_SHORT).show()
                return
            }
            if (titulo_edt.text.toString().isBlank()) {
                Toast.makeText(this, "Ingresa el titulo", Toast.LENGTH_SHORT).show()
                return
            }
            if (direccion_edt.text.toString().isBlank()) {
                Toast.makeText(this, "Ingresa la dirección", Toast.LENGTH_SHORT).show()
                return
            }

            val anuncio = Anuncio(
                precio = precio_edt.text.toString().toFloat(),
                titulo = titulo_edt.text.toString(),
                descripcion = descripcion_edt.text.toString(),
                telefono = telefono_edt.text.toString(),
                amueblado = if(amueblado_si.isChecked) 1 else 0,
                habitaciones = if(habitaciones_edt.text.toString().isBlank()) 0 else habitaciones_edt.text.toString().toInt(),
                banios = if(banios_edt.text.toString().isBlank()) 0 else banios_edt.text.toString().toInt(),
                area = if(area_edt.text.toString().isBlank()) 0F else area_edt.text.toString().toFloat(),
                direccion = direccion_edt.text.toString(),
                idcategoria = idcategoria
            )
            callServiceSaveAnuncio(anuncio)
        } catch (e: Exception){
            Toast.makeText(this, "Llene los campos correctamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun callServiceSaveAnuncio(anuncio: Anuncio) {

        GlobalScope.launch (Dispatchers.Main) {

            val response = withContext(Dispatchers.IO) {
                AnuncioUseCase(this@PublishActivity).saveAnuncio(anuncio)
            }

            when (response) {
                is ResponseType.Success -> {
                    Toast.makeText(this@PublishActivity, "Su anuncio fue publicado!", Toast.LENGTH_LONG).show()
                    finish()
                }
                is ResponseType.Error -> {
                    Toast.makeText(this@PublishActivity, response.value.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}