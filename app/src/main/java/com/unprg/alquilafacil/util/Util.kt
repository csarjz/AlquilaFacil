package com.unprg.alquilafacil.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.squareup.picasso.Picasso
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

fun ImageView.loadImage(url: String) {
    if (url.isNotBlank()) {
        Picasso.get().load(url).into(this)
    }
}