package com.moeiny.reza.covermore_testcode.ui.content

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.moeiny.reza.covermore_testcode.AndroidApplication
import com.moeiny.reza.covermore_testcode.R
import com.moeiny.reza.covermore_testcode.data.model.uimodel.ShowNewsModel
import com.moeiny.reza.covermore_testcode.databinding.ActivityContentBinding
import javax.inject.Inject

class ContentActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityContentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as AndroidApplication).component.injectContentActivity(this)

        /**
         * function setUpVies: Assign parameters and values
         */
        setUpView()

        /**
         * receiving data from intent and create an UI model object
         */
        var id = intent.extras?.getString(ID)
        var title = intent.extras?.getString(TITLE)
        var link = intent.extras?.getString(LINK)
        var content = intent.extras?.getString(CONTENT)
        var pubdate = intent.extras?.getString(PUBDATE)

        if (!id.isNullOrEmpty()) {
            var newsShow=ShowNewsModel(id,title,link,content,pubdate,"")
            mBinding.newsshow=newsShow
        }
    }

    private fun setUpView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_content)
    }

    companion object {
        const val ID = "id"
        const val TITLE = "title"
        const val LINK = "link"
        const val CONTENT = "content"
        const val PUBDATE = "pubdate"
    }
}
