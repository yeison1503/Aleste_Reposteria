package com.example.alestereposteria.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.alestereposteria.databinding.ActivityRegisterBinding
import com.example.alestereposteria.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        with(registerBinding){
            registerButton.setOnClickListener{
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val repPassword = repPasswordEditText.text.toString()

                if (password == repPassword){
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    intent.putExtra("email", email)
                    intent.putExtra("password", password)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}