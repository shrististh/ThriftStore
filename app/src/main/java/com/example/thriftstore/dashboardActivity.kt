package com.example.mainthriftstoreandroid

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class dashboardActivity  : AppCompatActivity() {
    private lateinit var txt1 : TextView
    private lateinit var txt2 : TextView
    private lateinit var txt3 : TextView
    private lateinit var txt4 : TextView
    private lateinit var txt5 : TextView
    private lateinit var txt6 : TextView
    private lateinit var txt7 : TextView
    private lateinit var txt8 : TextView
    private lateinit var txt9 : TextView
    private lateinit var image1 : ImageView
    private lateinit var image2 : ImageView
    private lateinit var image3 : ImageView
    private lateinit var image4 : ImageView
    private lateinit var image5 : ImageView
    private lateinit var image6 : ImageView
    private lateinit var image7 : ImageView
    private lateinit var image8 : ImageView
    private lateinit var image9 : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.bottomnav)
        setContentView(R.layout.activity_dashboard)

        getSupportActionBar()?.hide();
    }
}



//class OnScrollListener(val layoutManager: LinearLayoutManager,
//                       val adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>,
//                       val dataList: MutableList<Int>) : RecyclerView.OnScrollListener() {
//    var previousTotal = 0
//    var loading = true
//    val visibleThreshold = 10
//    var firstVisibleItem = 0
//    var visibleItemCount = 0
//    var totalItemCount = 0
//
//    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//        super.onScrolled(recyclerView, dx, dy)
//
//        visibleItemCount = recyclerView.childCount
//        totalItemCount = layoutManager.itemCount
//        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
//
//        if (loading) {
//            if (totalItemCount > previousTotal) {
//                loading = false
//                previousTotal = totalItemCount
//            }
//        }
//
//        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
//            val initialSize = dataList.size
//            updateDataList(dataList)
//            val updatedSize = dataList.size
//            recyclerView.post { adapter.notifyItemRangeInserted(initialSize, updatedSize) }
//            loading = true
//        }
//    }
//}