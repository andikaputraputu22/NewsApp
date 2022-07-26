package com.moonlightsplitter.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moonlightsplitter.newsapp.R
import com.moonlightsplitter.newsapp.databinding.AdapterCategoryBinding
import com.moonlightsplitter.newsapp.models.CategoryModel

class CategoryAdapter(
    val categories: List<CategoryModel>,
    val listener: OnAdapterListener
): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val itemsTextView = arrayListOf<TextView>()

    class ViewHolder(val binding: AdapterCategoryBinding): RecyclerView.ViewHolder(binding.root)

    interface OnAdapterListener {
        fun onClick(category: CategoryModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        itemsTextView.add(holder.binding.category)
        setColor(itemsTextView[0])
        holder.binding.category.text = category.name
        holder.itemView.setOnClickListener {
            listener.onClick(category)
            setColor(holder.binding.category)
        }
    }

    override fun getItemCount() = categories.size

    private fun setColor(textView: TextView) {
        itemsTextView.forEach { it.setBackgroundResource(R.color.white) }
        textView.setBackgroundResource(android.R.color.darker_gray)
    }
}