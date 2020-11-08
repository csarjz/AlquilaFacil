package com.example.myapp.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapp.R
import com.example.myapp.data.mapper.toDomain
import com.example.myapp.data.usecase.RegisterUseCase
import com.example.myapp.domain.model.Person
import com.example.myapp.ui.login.LoginActivity
import com.example.myapp.ui.main.MainActivity
import com.example.myapp.util.ResponseType
import com.example.myapp.util.Token
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setupView()
    }

    private fun setupView() {
        btn_register.setOnClickListener {
            val person = Person(
                nombre = edt_name.text.toString(),
                apellido = edt_lastname.text.toString(),
                telefono = edt_phone.text.toString(),
                email = edt_email.text.toString(),
                password = edt_password.text.toString()
            )
            callServiceCreateAccount(person)
        }
    }

    private fun callServiceCreateAccount(person: Person) {

        GlobalScope.launch (Dispatchers.Main) {

            val response = withContext(Dispatchers.IO) {
                RegisterUseCase(this@RegisterActivity).createAccount(person)
            }

            when (response) {
                is ResponseType.Success -> {
                    val loginResponse = response.value
                    if (loginResponse.token != null && loginResponse.persona != null) {
                        Token.saveToken(this@RegisterActivity, loginResponse.token!!)
                        val per = loginResponse.persona!!.toDomain()
                        per.saveLocal(this@RegisterActivity)
                        startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                        finish()
                    }else {
                        Toast.makeText(this@RegisterActivity, "Ocurrió un error", Toast.LENGTH_LONG).show()
                    }
                }
                is ResponseType.Error -> {
                    Toast.makeText(this@RegisterActivity, response.value.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}