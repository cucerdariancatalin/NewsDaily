package com.pratyakshkhurana.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_news.view.*

//Adapter class , we extend and pass viewHolder in RecyclerView.Adapter<>
//adapter needs data , which will come from activity or from API call
class NewsAdapter(private val items : ArrayList<String>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //whenever view is created it is called
        //inflater converts xml to view which is passed to viewHolder
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return NewsViewHolder(item)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        //binds data in holder
        val currentItem = items[position]
        holder.title.text = currentItem
    }

    override fun getItemCount(): Int {
        //called once, tells total items in list
        return items.size
    }
    //we basically pass all small items that repeat in recycler view to adapter through inflating by viewHolder
    //itemView is constraint layout
    class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title :TextView= itemView.tv
    }
}

