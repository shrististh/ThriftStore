package com.example.thriftstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        private lateinit var etUsername: EditText
        private lateinit var etPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)


    btnAddUser.setOnClickListener {

        val fname = etFname.text.toString()
        val lname = etLname.text.toString()
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()



    if (password != confirmPassword) {
        etPassword.error = "Password does not match"
        etPassword.requestFocus()
        return@setOnClickListener
    } else {
        val user =
            user(fname = fname, lname = lname, username = username, password = password)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = userRepository()
                val response = repository.registerUser(user)
                if(response.success == true){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@signupActivity, "Successfully registered", Toast.LENGTH_SHORT).show()
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