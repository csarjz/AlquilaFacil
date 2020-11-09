package com.unprg.alquilafacil.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Chat (
    var idchat: Int = 0,
    var estado: Int = 0,
    var fecha: String = String(),
    var idpersona: Int = 0,
    var idanuncio: Int = 0,
    var nombrePersona: String = String()
): Parcelable