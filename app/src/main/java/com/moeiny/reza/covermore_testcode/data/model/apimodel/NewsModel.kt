package com.moeiny.reza.covermore_testcode.data.model.apimodel

import com.moeiny.reza.covermore_testcode.data.model.apimodel.Feed
import com.moeiny.reza.covermore_testcode.data.model.apimodel.Item

data class NewsModel(
    val feed: Feed,
    val items: List<Item>,
    val status: String
)