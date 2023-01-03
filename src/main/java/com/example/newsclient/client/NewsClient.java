package com.example.newsclient.client;

import com.example.newsclient.model.NewsArticle;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.*;

public class NewsClient {
    private final String SERVER_API = "http://localhost:8080/api";
    private final WebClient webClient;

    public NewsClient(){ webClient = WebClient.builder().baseUrl(SERVER_API).build(); }

    public List<NewsArticle> getNews(){
        List<NewsArticle> newsArticles = Arrays.stream(webClient.get().uri("/news").retrieve().bodyToMono(NewsArticle[].class).block()).toList();
        return newsArticles;
    }

    public List<NewsArticle> getNewsByCategory(List<NewsArticle> newsArticlesList, String category){
        List<NewsArticle> filteredNewsArticle = new ArrayList<>();
        filteredNewsArticle = newsArticlesList.stream().filter(newsArticle -> newsArticle.getCategory().equals(category)).toList();
        return filteredNewsArticle;
    }
}
