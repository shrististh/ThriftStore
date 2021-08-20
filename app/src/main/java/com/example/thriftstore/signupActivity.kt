package com.example.thriftstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class signupActivity : AppCompatActivity() {

        private lateinit var etUsername: EditText
        private lateinit var etPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
    }


    if (password != confirmPassword) {
        etPassword.error = "Password does not match"
        etPassword.requestFocus()
        return@setOnClickListener
    } else {
        val user =
            User(fname = fname, lname = lname, username = username, password = password)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.registerUser(user)
                if(response.success == true){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@RegisterActivity, "Successfully registered", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RegisterActivity, ex.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }
}