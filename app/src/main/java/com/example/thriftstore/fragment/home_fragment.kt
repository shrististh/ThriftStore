package com.example.mainthriftstoreandroid.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainthriftstoreandroid.R
import com.example.mainthriftstoreandroid.api.serviceBuilder
import com.example.mainthriftstoreandroid.entities.product
import com.example.mainthriftstoreandroid.productadapter
import com.example.myandroidapplication.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class home_fragment : Fragment() {
    companion object{
        var listproduct: ArrayList<product> = ArrayList<product>()
    }

    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.activity_dashboard, container, false)
        recyclerView= view.findViewById(R.id.productRecyclerview)

        getallproducts()

        return view
    }



    private fun getallproducts() {
        listproduct = ArrayList<product>()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val productRepo = ProductRepository()
                val response = productRepo.getallproducts(serviceBuilder.token!!)
                Log.d("response", response.toString())
                if(response.success==true){
                    val product = response.data
                    product?.forEach { item->
                        listproduct.add(item)
                    }

                    withContext(Dispatchers.Main){
                        val adapter = productadapter(listproduct, requireContext())
                        recyclerView.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        recyclerView.adapter = adapter
                    }

                }
            }catch(ex : Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(activity, ex.toString(), Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}