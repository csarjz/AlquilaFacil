package com.unprg.alquilafacil.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment (
    var idpersona: Int = 0,
    var idanuncio: Int = 0,
    var fecha: String = String(),
    var nombre: String = String(),
    var apellido: String = String(),
    var personaImage: String = String(),
    var texto: String = String()
): Parcelable