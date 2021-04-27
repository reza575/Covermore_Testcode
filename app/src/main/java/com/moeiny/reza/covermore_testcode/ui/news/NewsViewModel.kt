package com.moeiny.reza.covermore_testcode.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moeiny.reza.covermore_testcode.core.result.Result
import com.moeiny.reza.covermore_testcode.data.model.uimodel.ShowNewsModel
import com.moeiny.reza.covermore_testcode.data.newsrepository.NewsRepository
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class NewsViewModel @Inject constructor (private val newsRepository: NewsRepository): ViewModel() {

    val showNewsLiveData = MutableLiveData<ShowNewsModel>()

    val newsLiveData = MutableLiveData<List<ShowNewsModel>>()

    val loadingLiveData = MutableLiveData<Boolean>()

    val errorLiveData = MutableLiveData<Boolean>()

    fun getNews() {
        newsRepository.fetchNewsInfo { result ->
            if (result is Result.Success) {

                /**
                 * Map News Data from API model to UI Model
                 */
                val newsList = result.data.items.map { news ->
                    ShowNewsModel(
                        id = news.guid.substringAfterLast("/").orEmpty(),
                        title = news.title.orEmpty(),
                        link = news.enclosure.link?.orEmpty(),
                        content = news.content?.orEmpty(),
                        pubDate = parseDateFormat(news.pubDate).orEmpty(),
                        thumbnail = news.thumbnail?.orEmpty()
                    )
                }
                newsLiveData.postValue(newsList)
            } else if (result is Result.Loading) {
                loadingLiveData.postValue(true)
            } else if (result is Result.Error) {
                errorLiveData.postValue(true)
            }
        }
    }

    fun onCardClicked(news: ShowNewsModel) {
        showNewsLiveData.postValue(news)
    }

    /**
     * Changing the format of date to needed format
     */
    fun parseDateFormat(time: String?): String? {
        val inputPattern = "yyyy-MM-dd HH:mm:ss"
        val outputPattern = "MMM dd,yyyy hh:mm a"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }
}