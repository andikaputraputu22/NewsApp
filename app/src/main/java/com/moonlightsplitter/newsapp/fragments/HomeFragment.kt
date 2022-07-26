package com.moonlightsplitter.newsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.moonlightsplitter.newsapp.DetailActivity
import com.moonlightsplitter.newsapp.R
import com.moonlightsplitter.newsapp.adapter.CategoryAdapter
import com.moonlightsplitter.newsapp.adapter.NewsAdapter
import com.moonlightsplitter.newsapp.databinding.CustomToolbarBinding
import com.moonlightsplitter.newsapp.databinding.FragmentHomeBinding
import com.moonlightsplitter.newsapp.models.CategoryModel
import com.moonlightsplitter.newsapp.models.NewsModel
import com.moonlightsplitter.newsapp.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val homeFragmentModule = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        bindingToolbar.textTitle.text = viewModel.title
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        bindingToolbar.title = viewModel.title
        binding.listCategory.adapter = categoryAdapter
        binding.listNews.adapter = newsAdapter

        viewModel.category.observe(viewLifecycleOwner, Observer {
            viewModel.fetchNews()
        })

        viewModel.news.observe(viewLifecycleOwner, Observer {
            newsAdapter.setData(it.articles)
        })

        viewModel.message.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private val categoryAdapter by lazy {
        CategoryAdapter(viewModel.categories, object : CategoryAdapter.OnAdapterListener {
            override fun onClick(category: CategoryModel) {
                viewModel.category.postValue(category.id)
            }
        })
    }

    private val newsAdapter by lazy {
        NewsAdapter(arrayListOf(), object : NewsAdapter.OnAdapterListener {
            override fun onClick(article: NewsModel.DataArticle) {
                startActivity(
                    Intent(requireActivity(), DetailActivity::class.java)
                        .putExtra("detailArticle", article)
                )
            }
        })
    }
}