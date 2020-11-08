package com.example.myapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapp.R
import com.example.myapp.data.mapper.toDomain
import com.example.myapp.data.request.login.LoginRequest
import com.example.myapp.data.usecase.LoginUseCase
import com.example.myapp.domain.model.Person
import com.example.myapp.ui.register.RegisterActivity
import com.example.myapp.ui.main.MainActivity
import com.example.myapp.util.ResponseType
import com.example.myapp.util.Token
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (checkSession()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            setupView()
        }

    }

    private fun setupView() {
        btn_login.setOnClickListener { view ->
            val userLogin = LoginRequest(
                edt_user.text.toString(),
                edt_password.text.toString()
            )

            if (!userLogin.email.isBlank() && !userLogin.password.isBlank()) {
                callServiceLogin(userLogin)
            } else {
                Toast.makeText(this@LoginActivity, "Completa los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btn_registrar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun checkSession (): Boolean {
        val token = Token.getToken(this)
        val person = Person.getLocal(this)
        if (token!=null && token.isNotBlank() && person.idpersona > 0) {
            return true
        }
        return false
    }

    private fun callServiceLogin(loginRequest: LoginRequest) {

        GlobalScope.launch (Dispatchers.Main) {

            val response = withContext(Dispatchers.IO) {
                LoginUseCase(this@LoginActivity).login(loginRequest)
            }

            when (response) {
                is ResponseType.Success -> {
                    val loginResponse = response.value
                    if (loginResponse.token != null && loginResponse.persona != null) {
                        Token.saveToken(this@LoginActivity, loginResponse.token!!)
                        val person = loginResponse.persona!!.toDomain()
                        person.saveLocal(this@LoginActivity)
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }else {
                        Toast.makeText(this@LoginActivity, "Ocurrió un error", Toast.LENGTH_LONG).show()
                    }
                }
                is ResponseType.Error -> {
                    Toast.makeText(this@LoginActivity, response.value.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}