package com.moonlightsplitter.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.moonlightsplitter.newsapp.databinding.ActivityDetailBinding
import com.moonlightsplitter.newsapp.databinding.CustomToolbarBinding
import com.moonlightsplitter.newsapp.models.DataArticle
import com.moonlightsplitter.newsapp.models.NewsModel

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val detailArticle by lazy {
        intent.getSerializableExtra("detailArticle") as DataArticle
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingToolbar = binding.toolbar
        setContentView(binding.root)

        setSupportActionBar(bindingToolbar.container)
        supportActionBar!!.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }

        loadWebview()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bookmark, menu)
        val menuBookmark = menu?.findItem(R.id.action_bookmark)
        menuBookmark?.setOnMenuItemClickListener {
            menuBookmark.setIcon(R.drawable.ic_check)
            true
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun loadWebview() {
        detailArticle.let {
            val web = binding.webView
            val setting = binding.webView.settings
            web.loadUrl(it.url?: "")
            web.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressTop.visibility = View.GONE
                }
            }

            setting.javaScriptCanOpenWindowsAutomatically = false
        }
    }
}