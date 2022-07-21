package com.pratyakshkhurana.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recycler view needs layout manager to ensure
//        how data will be shown on the screen, it interacts with layout manager
//        in this case it will be shown in form of vertical list
        recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        val items = fetchData()
//        passed data that is array list of string and context as listener for
//        handling click on each view in recycler view
//        link adapter to recycler view
        val adapter = NewsAdapter(items, this)

        //link adapter to recycler view
        recyclerview.adapter = adapter
    }

    //data passed to adapter which will be shown in recycler view
    private fun fetchData(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0 until 100) {
            list.add("Item ${i + 1}")
        }
        return list
    }

    override fun onItemClicked(item: String) {
        Toast.makeText(this, "clicked ", Toast.LENGTH_SHORT).show()
    }
}