package com.pratyakshkhurana.newsapp

//things we need to retrieve from articles[]
//those we will declare in data class for ease
//we will pass array of this News to adapter
//constructor needs these things
data class News(
    val title:String,
    val author:String,
    val url:String,
    val utlToImage:String
)