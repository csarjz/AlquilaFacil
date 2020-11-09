package com.unprg.alquilafacil.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.unprg.alquilafacil.data.apirestclient.APIUrl.BASE_IMAGE_URL
import java.text.NumberFormat

const val CASA = 1
const val DEPARTAMENTO = 2
const val HABITACION = 3
const val OFICINA = 4

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun numberFormat(number: Any) = NumberFormat.getInstance().format(number)

fun ImageView.loadAnuncioImage(imageName: String) {
    if (imageName.isNotBlank()) {
        Picasso.get().load("${BASE_IMAGE_URL}anuncio/${imageName}").into(this)
    } else {
        Picasso.get().load("${BASE_IMAGE_URL}anuncio/default.jpg").into(this)
    }
}

fun ImageView.loadPersonImage(imageName: String) {
    if (imageName.isNotBlank()) {
        Picasso.get().load("${BASE_IMAGE_URL}persona/${imageName}").into(this)
    } else {
        Picasso.get().load("${BASE_IMAGE_URL}persona/default.jpg").into(this)
    }
}