package com.unprg.alquilafacil.domain.model

import android.content.Context
import android.os.Parcelable
import com.unprg.alquilafacil.util.MySharedPreferences
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize
import java.lang.Exception

@Parcelize
class Person(
     var idpersona: Int = 0,
     var estado: Int = 0,
     var nombre: String = String(),
     var apellido: String = String(),
     var telefono: String = String(),
     var email: String = String(),
     var password: String = String(),
     var direccion: String = String(),
     var direccionmapa: String = String(),
     var latitud: Float = 0F,
     var longitud: Float = 0F,
     var idrol: Int = 0
): Parcelable {
    fun fullName(): String {
        return "$nombre $apellido"
    }

    fun saveLocal(context: Context) {
        val mySharedPreferences = MySharedPreferences(context)
        mySharedPreferences.put("PERSONA", Gson().toJson(this))
        mySharedPreferences.save()
    }

    companion object {
        fun getLocal(context: Context): Person {
            try {
                val mySharedPreferences = MySharedPreferences(context)
                return Gson().fromJson(mySharedPreferences.getKey("PERSONA"), Person::class.java)
            }catch (e: Exception) {
                return Person()
            }
        }
    }
}