package com.example.thriftstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.thriftstore.entities.user
import com.example.thriftstore.repository.userRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class signupActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var etLocation: EditText
    private lateinit var btnRegister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        etLocation = findViewById(R.id.etLocation)


        btnRegister.setOnClickListener {

            val email = etEmail.text.toString()
            val Password = etPassword.text.toString()
            val location = etLocation.text.toString()
            val Confirmpassword = etConfirmPassword.text.toString()



            if (Password != Password) {
                etPassword.error = "Password does not match"
                etPassword.requestFocus()
                return@setOnClickListener
            } else {
                val user = user(
                    email = email,
                    password = Password,
                    location = location)

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val repository = userRepository()
                        val response = repository.registerUser(user)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    this@signupActivity,
                                    "Successfully registered",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@signupActivity, ex.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

            }
        }
    }
}


