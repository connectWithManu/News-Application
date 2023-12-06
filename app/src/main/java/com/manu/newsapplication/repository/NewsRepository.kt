package com.manu.newsapplication.repository

import com.manu.newsapplication.api.RetrofitInstance
import com.manu.newsapplication.db.ArticleDatabase
import com.manu.newsapplication.model.Article

class NewsRepository(val db: ArticleDatabase) {
    suspend fun getHeadLines(countyCode: String, pageNum: Int) =
        RetrofitInstance.api.getHeadLines(countyCode, pageNum)

    suspend fun searchNews(searchQuery: String, pageNum: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNum)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    suspend fun delete(article: Article) = db.getArticleDao().deleteArticle(article)

    fun getFavoritesNews() = db.getArticleDao().getAllArticles()

}