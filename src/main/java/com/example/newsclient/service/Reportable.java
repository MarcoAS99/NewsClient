package com.example.newsclient.service;

import com.example.newsclient.model.NewsArticle;

import java.util.List;

public interface Reportable {
    String reportMinimal(List<NewsArticle> newsArticleList);
    String reportAll(List<NewsArticle> newsArticleList);
    String reportByCategory(List<NewsArticle> newsArticleList);
}
