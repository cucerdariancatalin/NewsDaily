package com.pratyakshkhurana.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_news.view.*

//extend NewsAdapter class to recycler view adapter class and pass viewHolder
//Adapter class , we extend and pass viewHolder in RecyclerView.Adapter<>
//adapter needs data , which will come from activity or from API call or from database
//adapter needs data so it passed to it's constructor
class NewsAdapter(private val items: ArrayList<String>, private val listener: NewsItemClicked) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //whenever view is created it is called
        //LayoutInflater class, converts xml to view which is passed to viewHolder
        //converts R.layout.item_news (xml) to view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        //binds data in the holder
        //extract data from current position
        //holder is an instance of NewsViewHolder
        val currentItem = items[position]
        holder.title.text = currentItem
    }

    override fun getItemCount(): Int {
        //called only once, returns total items in list
        return items.size
    }

    //we basically pass all small items that repeat in recycler view to adapter through inflating by viewHolder
    //itemView is constraint layout
    //pass itemView to adapter
    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.tv
    }
}

//used as callback which will be handled by main activity for handling clicks of itemView
//used for making callback
interface NewsItemClicked {
    fun onItemClicked(item: String)
}

