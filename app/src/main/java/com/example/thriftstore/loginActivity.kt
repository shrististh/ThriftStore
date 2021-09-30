package com.example.thriftstore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.thriftstore.repository.userRepository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class loginActivity : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

    }

    private fun login() {
        val username = etEmail.text.toString()
        val password = etPassword.text.toString()


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = userRepository()
//                val response = repository.login(username, password)
//                if (response.success == true) {
//                    // Save token
////                        serviceBuilder.token = "Bearer ${response.token}"
//                    //Save username and password in shared preferences
//                    // saveUsernamePassword()
//                    startActivity(
//                        Intent(
//                            this@loginActivity,
//                            dashboardActivity::class.java
//                        )
//                    )
//                    finish()
//                } else {
//                    withContext(Dispatchers.Main) {
////                            val snack =
////                                Snackbar.make(
////                                    linearLayout,
////                                    "Invalid credentials",
////                                    Snackbar.LENGTH_LONG
////                                )
////                            snack.setAction("OK", View.OnClickListener {
////                                snack.dismiss()
////                            })
////                            snack.show()
//                        print("abc")
//                    }
//                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d("Error", ex.toString())

                    Toast.makeText(
                        this@loginActivity,
                        ex.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}
