package com.example.newsclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootApplication
public class NewsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsClientApplication.class, args);

        NewsClient client = new NewsClient(WebClient.builder());

        List<NewsArticle> news = List.of(client.getNews());
        System.out.println(client.getSocial(news));
        client.reportCategories(news);
        client.report(news);
    }

}
