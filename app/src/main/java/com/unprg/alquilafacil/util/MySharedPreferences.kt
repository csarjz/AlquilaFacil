package com.unprg.alquilafacil.util

import android.content.Context

class MySharedPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("ALQUILA_FACIL", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun put(key:String, value : String) {
        this.editor.putString(key, value)
    }
    fun save() {
        this.editor.apply()
    }

    fun getKey(key: String) : String? {
        return this.sharedPreferences.getString(key, null)
    }

}