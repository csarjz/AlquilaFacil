package com.example.myapp.util

import android.content.Context

class Token {
     companion object {
         const val TOKEN = "TOKEN_SESSION"

         fun saveToken(context: Context, token: String) {
             val mySharedPreferences = MySharedPreferences(context)
             mySharedPreferences.put(TOKEN, token)
             mySharedPreferences.save()
         }

         fun getToken(context: Context) = MySharedPreferences(context).getKey(TOKEN)

         fun deleteToken(context: Context) = saveToken(context, "")
     }
}