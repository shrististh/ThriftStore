package com.example.thriftstore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.thriftstore.R

class home_fragment : Fragment() {
    private lateinit var search: SearchView
    private lateinit var txttrayname: TextView
    private lateinit var txttrayprice: TextView
    private lateinit var txtvasename: TextView
    private lateinit var txtvaseprice: TextView
    private lateinit var txtsweatername: TextView
    private lateinit var txtsweaterprice: TextView
    private lateinit var txtdressname: TextView
    private lateinit var txtdressprice: TextView
    private lateinit var txtblazername: TextView
    private lateinit var txtblazerprice: TextView
    private lateinit var btndress: Button
    private lateinit var btnblazer: Button
    private lateinit var btnshirt: Button
    private lateinit var btnpant: Button
    private lateinit var btncap: Button
    private lateinit var btnwhitedress: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.homefragment, container, false)


    }
}