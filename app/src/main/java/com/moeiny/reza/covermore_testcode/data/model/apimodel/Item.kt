package com.moeiny.reza.covermore_testcode.data.model.apimodel

import com.moeiny.reza.covermore_testcode.data.model.apimodel.Enclosure

data class Item(
    val author: String,
    val categories: List<String>,
    val content: String,
    val description: String,
    val enclosure: Enclosure,
    val guid: String,
    val link: String,
    val pubDate: String,
    val thumbnail: String,
    val title: String
)