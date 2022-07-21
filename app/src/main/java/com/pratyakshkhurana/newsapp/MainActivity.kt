package com.pratyakshkhurana.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {
    //accessible from everywhere
    private lateinit var mAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recycler view needs layout manager to ensure
//        how data will be shown on the screen, it interacts with layout manager
//        in this case it will be shown in form of vertical list
        recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        fetchData()
//        passed data that is array list of string and context as listener for
//        handling click on each view in recycler view
//        link adapter to recycler view
        mAdapter = NewsAdapter(this)

        //link adapter to recycler view
        recyclerview.adapter = mAdapter
    }

    //data passed to adapter which will be shown in recycler view
    private fun fetchData() {
        //API to fetch data
        val url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"

        // making JsonObjectRequest passed to queue then processing takes place,
        //and it listens in listener / errorListener
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                //since we need to take 20 news at a time and pass to recycler view
                //we retrieve Json array
                val newsJsonArray = it.getJSONArray("articles")
                //pass newsArray to adapter as data
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length()) {
                    //i th object
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    //creating object of data class, then adding it to
                    //list we need to pass to adapter as data from database
                    // or through API call
                    //parsing of data happening below
                    val newsObject = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(newsObject)
                }
                //passed data to adapter
                mAdapter.updateNews(newsArray)
            },
            {
            }
        )

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
    }
}