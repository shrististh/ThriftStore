package com.example.mainthriftstoreandroid

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mainthriftstoreandroid.model.user
import com.example.mainthriftstoreandroid.repository.userRepository
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
    private lateinit var btnsignup: Button
//    private lateinit var btnlogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        getSupportActionBar()?.hide();

        etEmail = findViewById(R.id.etEmailSignup)
        etPassword = findViewById(R.id.etPasswordSignUp)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        etLocation = findViewById(R.id.etLocation)
        btnsignup = findViewById(R.id.btnRegisterSignUp)
//        btnlogin = findViewById(R.id.btnLogin)

        btnsignup.setOnClickListener {
            registerUser()

            val intent = Intent(this@signupActivity, loginActivity::class.java)

            startActivity(intent)

        }



//        btnlogin.setOnClickListener {
//            val intent = Intent(this, loginActivity::class.java)
//
//            startActivity(intent);
//        }
    }

    private fun registerUser(){

            val email = etEmail.text.toString()
            val Password = etPassword.text.toString()
            val location = etLocation.text.toString()
            val Confirmpassword = etConfirmPassword.text.toString()

//            Log.v("shristi", "Line43")

            if (Password != Confirmpassword) {
                etPassword.error = "Password does not match"
                etPassword.requestFocus()
                return

            } else {
                val user = user(
                    email = email,
                    password = Password,
                    location = location
                )



//                CoroutineScope(Dispatchers.IO).launch {
//                    try {
//                        val repository = userRepository()
//                        val response = repository.registerUser(user)
//                        if (response.success == true) {
//                            withContext(Dispatchers.Main) {
//                                Toast.makeText(
//                                    this@signupActivity,
//                                    "Successfully registered",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
////                            startActivity(Intent(this@signupActivity, loginActivity::class.java))
//                        }

                CoroutineScope(Dispatchers.IO).launch {
                    //StudentDB(this@RegisterUserActivity).getUserDAO().registerUser(user)
                    val repository = userRepository()
                    val response = repository.registerUser(user)

                    try{
                        Log.v("Response......", response.toString())
                        if (response.success ==true){
                            withContext(Dispatchers.Main){
                                Toast.makeText(this@signupActivity, "Registered", Toast.LENGTH_SHORT).show()
                            }



//                            startActivity(Intent(this@signupActivity, loginActivity::class.java))
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
