package com.unprg.alquilafacil.ui.publish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.unprg.alquilafacil.R
import com.unprg.alquilafacil.data.usecase.AnuncioUseCase
import com.unprg.alquilafacil.domain.model.Anuncio
import com.unprg.alquilafacil.util.OFICINA
import com.unprg.alquilafacil.util.ResponseType
import kotlinx.android.synthetic.main.fragment_publish_anuncio.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class PublishAnuncioFragment(val idcategoria: Int) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_publish_anuncio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        btnSiguiente.setOnClickListener{ publicar() }

        if (idcategoria == OFICINA) {
            amobladoTextView.visibility = View.GONE
            amobladoRadioGroup.visibility = View.GONE
            habitacionesTextView.visibility = View.GONE
            habitaciones_edt.visibility = View.GONE
            baniosTextView.visibility = View.GONE
            banios_edt.visibility = View.GONE
        }
    }

    private fun publicar() {
        try {
            if (precio_edt.text.toString().isBlank() || precio_edt.text.toString().toFloat() <= 0) {
                Toast.makeText(requireContext(), "Precio no válido", Toast.LENGTH_SHORT).show()
                return
            }
            if (titulo_edt.text.toString().isBlank()) {
                Toast.makeText(requireContext(), "Ingresa el titulo", Toast.LENGTH_SHORT).show()
                return
            }
            if (direccion_edt.text.toString().isBlank()) {
                Toast.makeText(requireContext(), "Ingresa la dirección", Toast.LENGTH_SHORT).show()
                return
            }

            val anuncio = Anuncio(
                precio = precio_edt.text.toString().toFloat(),
                titulo = titulo_edt.text.toString(),
                descripcion = descripcion_edt.text.toString(),
                telefono = telefono_edt.text.toString(),
                amueblado = if(amoblado_si.isChecked) 1 else 0,
                habitaciones = if(habitaciones_edt.text.toString().isBlank()) 0 else habitaciones_edt.text.toString().toInt(),
                banios = if(banios_edt.text.toString().isBlank()) 0 else banios_edt.text.toString().toInt(),
                area = if(area_edt.text.toString().isBlank()) 0F else area_edt.text.toString().toFloat(),
                direccion = direccion_edt.text.toString(),
                idcategoria = idcategoria
            )
            callServiceSaveAnuncio(anuncio)
        } catch (e: Exception){
            Toast.makeText(requireContext(), "Llene los campos correctamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun callServiceSaveAnuncio(anuncio: Anuncio) {

        GlobalScope.launch (Dispatchers.Main) {

            val response = withContext(Dispatchers.IO) {
                AnuncioUseCase(requireContext()).saveAnuncio(anuncio)
            }

            when (response) {
                is ResponseType.Success -> {
                    (requireActivity() as PublishActivity).idanuncio = response.value.idanuncio

                    val fragment = PublishImagesFragment(response.value.idanuncio)
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentPublish, fragment)
                        .commit()
                }
                is ResponseType.Error -> {
                    Toast.makeText(requireContext(), response.value.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}