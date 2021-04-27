package com.moeiny.reza.covermore_testcode.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.covermore_testcode.R
import com.moeiny.reza.covermore_testcode.data.model.uimodel.ShowNewsModel
import com.moeiny.reza.covermore_testcode.databinding.UserBinding
import com.moeiny.reza.covermore_testcode.databinding.UserBindingTop

class NewsAdapter(private val onCardClicked: (news: ShowNewsModel) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var newsList: List<ShowNewsModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class NewsViewHolder : RecyclerView.ViewHolder {
         var headerBinding: UserBindingTop? = null
         var regularItemBinding: UserBinding? = null

        constructor(binding: UserBindingTop) : super(binding.root) {
            headerBinding = binding
        }

        constructor(binding: UserBinding) : super(binding.getRoot()) {
            regularItemBinding = binding
        }

        /**
         * setup multiple binding
         */
        fun bind(modelViewShowNews: ShowNewsModel) {
            if(headerBinding!=null)
                this.headerBinding?.newsshow = modelViewShowNews

            if(regularItemBinding!=null)
                this.regularItemBinding?.newsshow = modelViewShowNews
        }
    }

    /**
     * passing correct view layout by attention to type of viewtype
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding
        when (viewType) {
            CELL_TYPE_HEADER -> {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.newsrowtop,
                    parent,
                    false
                )
                return NewsViewHolder(binding as UserBindingTop)
            }
            else -> {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.newsrow,
                    parent,
                    false
                )
                return NewsViewHolder(binding as UserBinding)
            }
        }
    }

    override fun getItemCount(): Int {
        return newsList.count()
    }

    /**
     * handle setOnclick listener by attention of all viewtype
     */
    override fun onBindViewHolder(holderNews: NewsViewHolder, position: Int) {
        var info = newsList.get(position)

        when (holderNews.getItemViewType()) {
             CELL_TYPE_HEADER -> {
                 holderNews.headerBinding?.cardPhotorowParent?.setOnClickListener {
                     onCardClicked(info!!)
                 }
             }
            CELL_TYPE_REGULAR_ITEM -> {
                holderNews.regularItemBinding?.cardPhotorowParent?.setOnClickListener {
                    onCardClicked(info!!)
                }
            }
        }
        holderNews.bind(info)
    }

    /**
     * config viewtype for data items
     */
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            CELL_TYPE_HEADER
        } else {
            CELL_TYPE_REGULAR_ITEM
        }
    }

    companion object{
        private val CELL_TYPE_HEADER = 0
        private val CELL_TYPE_REGULAR_ITEM = 1
    }
}

