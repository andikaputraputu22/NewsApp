package com.moonlightsplitter.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moonlightsplitter.newsapp.R
import com.moonlightsplitter.newsapp.databinding.CustomToolbarBinding
import com.moonlightsplitter.newsapp.databinding.FragmentBookmarkBinding
import com.moonlightsplitter.newsapp.viewmodel.BookmarkViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val bookmarkFragmentModule = module {
    factory { BookmarkFragment() }
}

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: BookmarkViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        bindingToolbar.textTitle.text = viewModel.title
        bindingToolbar.title = viewModel.title
    }
}