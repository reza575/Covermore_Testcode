package com.moeiny.reza.covermore_testcode.data.retrofit


import com.moeiny.reza.covermore_testcode.data.model.apimodel.NewsModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // Communicates responses from a server are executed on the background thread which performed the request
    @GET("v1/api.json?rss_url=http://www.abc.net.au/news/feed/51120/rss.xml")
    fun getNewsInfo():Call<NewsModel>
}