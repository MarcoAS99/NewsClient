package com.example.newsclient.service;

import com.example.newsclient.model.NewsArticle;
import lombok.Data;

import java.util.*;

@Data
public class ReportGenerator implements Reportable{

    @Override
    public String reportMinimal(List<NewsArticle> newsArticleList) {
        List<String> headers = new ArrayList<>();
        headers.add("Title");
        headers.add("Category");

        StringBuilder sb = setHeaders(headers);
        newsArticleList.forEach(obj -> sb.append(obj.getTitle())
                .append(",")
                .append(obj.getCategory())
                .append("\n"));

        return sb.toString();
    }

    @Override
    public String reportAll(List<NewsArticle> newsArticleList) {
        List<String> headers = new ArrayList<>();
        headers.add("Title");
        headers.add("Description");
        headers.add("Id");

        StringBuilder sb = setHeaders(headers);
        newsArticleList.forEach(obj -> sb.append(obj.getTitle())
                .append(",")
                .append(obj.getDescription())
                .append(",")
                .append(obj.getId())
                .append("\n"));

        return sb.toString();
    }

    @Override
    public String reportByCategory(List<NewsArticle> newsArticleList) {
        List<String> headers = new ArrayList<>();
        headers.add("Category");
        headers.add("Value");

        StringBuilder sb = setHeaders(headers);
        Set<String> categoriesSet = new HashSet<>();
        newsArticleList.forEach(obj -> categoriesSet.add(obj.getCategory()));
        categoriesSet.forEach(category -> sb.append(category).append(",")
                .append(newsArticleList.stream().filter(obj -> category.equals(obj.getCategory())).count())
                .append("\n"));

        return sb.toString();
    }

    private StringBuilder setHeaders(List<String> headers){
        StringBuilder stringBuilder = new StringBuilder();
        headers.stream().forEach(item -> stringBuilder.append(item).append(","));
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("\n");
        return stringBuilder;
    }
}
