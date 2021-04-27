package com.moeiny.reza.covermore_testcode.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

/*
 * this object is essential to showing image on ImageView for viewitems of ViewAdapter
    when we use databinding
*/
object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(view: ImageView, url: String) {
        /**
         * Using Glige to load image asynchronously
         */
        Glide.with(view.context).load(url).into(view)
    }
}