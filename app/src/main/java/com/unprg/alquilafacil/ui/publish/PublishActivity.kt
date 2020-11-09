package com.unprg.alquilafacil.ui.publish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.unprg.alquilafacil.R
import com.unprg.alquilafacil.data.usecase.AnuncioUseCase
import com.unprg.alquilafacil.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PublishActivity : AppCompatActivity() {
    private var idcategoria = 0
    var idanuncio = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        idcategoria = intent.getIntExtra("idcategoria", 0)
        if(idcategoria <= 0) {
            finish()
            return
        }
        when (idcategoria) {
            CASA -> supportActionBar?.title = "Publicar Casa"
            DEPARTAMENTO -> supportActionBar?.title = "Publicar Departamento"
            HABITACION -> supportActionBar?.title = "Publicar Habitación"
            OFICINA -> supportActionBar?.title = "Publicar Oficina"
        }

        val fragment = PublishAnuncioFragment(idcategoria)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentPublish, fragment)
            .commit()
    }

    private fun showExitDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Seguro que desa salir?")
        builder.setMessage("Si sales perderas todos los cambios")

        builder.setPositiveButton("Salir") { _, _ ->
            val fragment = supportFragmentManager.findFragmentById(R.id.fragmentPublish)
            if (fragment != null && fragment is PublishImagesFragment && idanuncio > 0) {
                callServiceDeleteAnuncio(idanuncio)
            }
            finish()
        }
        builder.setNegativeButton("Cancelar", null)

        builder.create().show()
    }

    private fun callServiceDeleteAnuncio(idanuncio: Int) {
        GlobalScope.launch (Dispatchers.IO) {
            AnuncioUseCase(this@PublishActivity).delteAnuncio(idanuncio, 1)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    override fun onBackPressed() {
        showExitDialog()
    }

}