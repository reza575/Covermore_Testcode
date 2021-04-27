package com.moeiny.reza.covermore_testcode.data.newsrepository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.moeiny.reza.covermore_testcode.core.result.Result
import com.moeiny.reza.covermore_testcode.data.model.apimodel.NewsModel
import com.moeiny.reza.covermore_testcode.data.retrofit.ApiService
import javax.inject.Inject

interface NewsRepository {
    fun fetchNewsInfo(onResult: (result: Result<NewsModel>) -> Unit)
}

class NewsRepositoryDefault @Inject constructor(private val apiService: ApiService) : NewsRepository{
    override fun fetchNewsInfo(onResult: (result: Result<NewsModel>) -> Unit) {

        onResult(Result.Loading)

        apiService.getNewsInfo().enqueue(object :Callback<NewsModel>{
            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                onResult(Result.Error(t))
            }

            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                response.body()?.let {
                    onResult(Result.Success(it))
                }
            }
        })
    }
}

