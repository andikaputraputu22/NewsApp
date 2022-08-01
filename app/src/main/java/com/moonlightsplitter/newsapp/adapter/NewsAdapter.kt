package com.moonlightsplitter.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moonlightsplitter.newsapp.databinding.AdapterNewsBinding
import com.moonlightsplitter.newsapp.models.DataArticle
import com.moonlightsplitter.newsapp.models.NewsModel
import com.moonlightsplitter.newsapp.utils.DateUtil

class NewsAdapter(
    val articles: ArrayList<DataArticle>,
    val listener: OnAdapterListener
): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(val binding: AdapterNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: DataArticle) {
            binding.article = article
            binding.date = DateUtil()
        }
    }

    interface OnAdapterListener {
        fun onClick(article: DataArticle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
        holder.itemView.setOnClickListener {
            listener.onClick(article)
        }
    }

    override fun getItemCount() = articles.size

    fun setData(data: List<DataArticle>) {
        articles.clear()
        articles.addAll(data)
        notifyDataSetChanged()
    }
}