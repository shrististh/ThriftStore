package com.example.thriftstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thriftstore.fragment.home_fragment
import com.example.thriftstore.fragment.map_fragment
import com.example.thriftstore.fragment.addtocart_fragment
import com.example.thriftstore.fragment.user_fragment


class bottomNav : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottomnav)

        val homeFragment = home_fragment()
        val addtocartFragment = addtocart_fragment()
        val userFragment = user_fragment()
        val mapFragment = map_fragment()
    }
}

