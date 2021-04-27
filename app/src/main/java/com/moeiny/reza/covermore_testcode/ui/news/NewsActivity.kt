package com.moeiny.reza.covermore_testcode.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.moeiny.reza.covermore_testcode.AndroidApplication
import com.moeiny.reza.covermore_testcode.R
import androidx.lifecycle.ViewModelProvider
import com.moeiny.reza.covermore_testcode.databinding.ActivityNewsBinding
import com.moeiny.reza.covermore_testcode.ui.content.ContentActivity
import javax.inject.Inject


class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    lateinit var mBinding:ActivityNewsBinding

    @Inject
    lateinit var viewModelFactory:ViewModelProvider.Factory

    private val adapter: NewsAdapter by lazy {
        NewsAdapter(viewModel::onCardClicked)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as AndroidApplication).component.injectNewsActivity(this)
        /**
         * function setUpVies: Assign parameters and values
         */
        setUpView()

        /**
         * SetUp all the livedata parameters to start their job(Observing data)
         */
        setupLiveData()
    }

    private fun setupLiveData() {
        /**
         * observing the list of rows of API to update data on recycler view after any changing
         */
        viewModel.newsLiveData.observe(this, Observer { newsList ->
            mBinding.loadingPanel.visibility =
                if (newsList.isNotEmpty()) View.GONE else View.VISIBLE
            adapter.newsList = newsList

        })

        /**
         * observing data fetching from API to update loading state
         */
        viewModel.loadingLiveData.observe(this, Observer {
            mBinding.loadingPanel.visibility = if (it) View.VISIBLE else View.GONE
        })

        /**
         * observing for any error during data fetching from API to update error state
         */
        viewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, "error on loading Data", Toast.LENGTH_SHORT).show()
        })

        /**
         * observing for any data that clicked by the user
         */
        viewModel.showNewsLiveData.observe(this, Observer { news ->
            val intent = Intent(this, ContentActivity::class.java)
            intent.putExtra(ContentActivity.ID, news.id)
            intent.putExtra(ContentActivity.TITLE, news.title)
            intent.putExtra(ContentActivity.LINK, news.link)
            intent.putExtra(ContentActivity.CONTENT, news.content)
            intent.putExtra(ContentActivity.PUBDATE, news.pubDate)
            this.startActivity(intent)
        })
    }

    private fun setUpView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        viewModel = viewModelFactory.create(NewsViewModel::class.java)
        viewModel.getNews()
        mBinding.newsRecyclerview.adapter = adapter
    }
}
