package com.example.thriftstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    private fun login() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()


    CoroutineScope(Dispatchers.IO).launch {
        try {
            val repository = UserRepository()
            val response = repository.login(username, password)
            if (response.success == true) {
                // Save token
                ServiceBuilder.token = "Bearer ${response.token}"
                //Save username and password in shared preferences
                // saveUsernamePassword()
                startActivity(
                    Intent(
                        this@LoginActivity,
                        DashboardActivity::class.java
                    )
                )
                finish()
            } else {
                withContext(Dispatchers.Main) {
                    val snack =
                        Snackbar.make(
                            linearLayout,
                            "Invalid credentials",
                            Snackbar.LENGTH_LONG
                        )
                    snack.setAction("OK", View.OnClickListener {
                        snack.dismiss()
                    })
                    snack.show()
                }
            }
        } catch (ex: Exception) {
            withContext(Dispatchers.Main) {
                Log.d("Error", ex.toString())

                Toast.makeText(
                    this@LoginActivity,
                    ex.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

private fun askRunTimerPermission() {

}

}