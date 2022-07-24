package com.pratyakshkhurana.newsapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news.view.*

//extend NewsAdapter class to recycler view adapter class and pass viewHolder
//Adapter class , we extend and pass viewHolder in RecyclerView.Adapter<>
//adapter needs data , which will come from activity or from API call or from database
//adapter needs data so it passed to it's constructor
class NewsAdapter(private val listener: NewsItemClicked) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    //data through API call
    private val items: ArrayList<News> = ArrayList()

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
        holder.title.text = currentItem.title
        holder.author.text = currentItem.author
        holder.title.setTextColor(Color.parseColor("#ffffff"))
        holder.author.setTextColor(Color.parseColor("#ffffff"))
        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.img)
    }

    override fun getItemCount(): Int {
        //called only once, returns total items in list
        return items.size
    }

    //to update items of adapter
    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedItems: ArrayList<News>) {
        items.clear()
        items.addAll(updatedItems)

        //tells adapter to refresh adapter and all 3 functions are
        //called again to refresh data
        notifyDataSetChanged()
    }

    //we basically pass all small items that repeat in recycler view to adapter through inflating by viewHolder
    //itemView is constraint layout
    //pass itemView to adapter
    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.tv
        val author: TextView = itemView.author
        val img: ImageView = itemView.image
    }
}

//used as callback which will be handled by main activity for handling clicks of itemView
//used for making callback
interface NewsItemClicked {
    fun onItemClicked(item: News)
}

