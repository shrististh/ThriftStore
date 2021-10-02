package com.example.mainthriftstoreandroid

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mainthriftstoreandroid.api.serviceBuilder
import com.example.mainthriftstoreandroid.repository.userRepository
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response

class loginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnsignup: Button
    public lateinit var linearLayout: LinearLayout
    private lateinit var btnlogin : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getSupportActionBar()?.hide();


        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnsignup = findViewById(R.id.btnRegister)
        btnlogin = findViewById(R.id.btnLogin)

        btnsignup.setOnClickListener {
            val intent = Intent(this, signupActivity::class.java)

            startActivity(intent);
//            startActivity(Intent(this@loginActivity, signupActivity::class.java))
        }

        btnlogin.setOnClickListener {
            loginAPI()
        }

    }

    private fun loginAPI() {
        val Email = etEmail.text.toString()
        val password = etPassword.text.toString()


        CoroutineScope(Dispatchers.IO).launch  {

            try {
                val repository = userRepository()
                val response = repository.login(Email, password)
                println(response.success)
                println(response.token)
                if (response.success == true) {

                    // Save token
                    serviceBuilder.token = "Bearer ${response.token}"
                    //Save username and password in shared preferences
                    // saveUsernamePassword()
                    startActivity(
                        Intent(
                            this@loginActivity,
                            dashboardActivity::class.java
                        )
                    )
                    finish()
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@loginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()

                    //                        val snack =
//                            Snackbar.make(
//                                linearLayout,
//                                "Invalid credentials",
//                                Snackbar.LENGTH_LONG
//                            )
//                        snack.setAction("OK", View.OnClickListener {
//                            snack.dismiss()
//                        })
//                        snack.dismiss()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d("Error", ex.printStackTrace().toString())
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



