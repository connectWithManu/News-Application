package com.manu.newsapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.manu.newsapplication.databinding.ItemNewsBinding
import com.manu.newsapplication.model.Article

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsVH>() {
    inner class NewsVH(val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Article> () {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsVH(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        val article = differ.currentList[position]

        holder.binding.articleImage.load(article.urlToImage)
        holder.binding.articleSource.text = article.source.name
        holder.binding.articleTitle.text = article.title
        holder.binding.articleDescription.text = article.description
        holder.binding.articleDateTime.text = article.publishedAt

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(article)
            }
        }
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}