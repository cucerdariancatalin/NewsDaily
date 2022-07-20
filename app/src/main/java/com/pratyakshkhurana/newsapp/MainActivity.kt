package com.pratyakshkhurana.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recycler view needs layout manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        //link adapter to recycler view
        val items = fetchData()
        val adapter = NewsAdapter(items)
        //link adapter to recycler view
        recyclerview.adapter  = adapter
    }
    //data passed to adapter
    private fun fetchData() : ArrayList<String>{
        val list = ArrayList<String>()
        for(i in 0 until 100){
            list.add("Item ${i+1}")
        }
        return list
    }

}