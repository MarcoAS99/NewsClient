package com.example.newsclient;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsClient {
    private final String api = "http://localhost:8080/api";
    private final WebClient webClient;

    public NewsClient(WebClient.Builder builder){
        webClient = builder.baseUrl(api).build();
    }

    public NewsArticle[] getNews(){
        return webClient.get().uri("/news").retrieve().bodyToMono(NewsArticle[].class).block();
    }

    public List<NewsArticle> getSocial(List<NewsArticle> l){
        List<NewsArticle> socialArticles = new ArrayList<>();
/*
        for (NewsArticle a: l) {
            if(a.getCategory().equals("SOCIAL")) socialArticles.add(a);
        }
*/
        socialArticles = l.stream().filter(newsArticle -> newsArticle.getCategory().equals("SOCIAL")).toList();

        //Write to CSV
        List<String> headers = new ArrayList<>();
        headers.add("Title");
        headers.add("Description");
        headers.add("Id");
        toCSV("SocialNews", socialArticles, headers);

        return socialArticles;
    }

    public Map<String, Integer> reportCategories(List<NewsArticle> l){
        Map<String, Integer> report = new HashMap<>();
        for (NewsArticle a: l) {
            String category = a.getCategory();
            int value = report.get(category) == null ? 1 : report.get(category)+1;
            report.put(category, value);
        }

        //Write to CSV
        List<String> headers = new ArrayList<>();
        headers.add("Category");
        headers.add("Value");
        MapToCSV("reportCategories", report, headers);

        return report;
    }

    public Map<String,String> report(List<NewsArticle> l){
        Map<String, String> report = new HashMap<>();
        for (NewsArticle a: l){ report.put(a.getTitle(), a.getCategory()); }

        //Write to CSV
        List<String> headers = new ArrayList<>();
        headers.add("Title");
        headers.add("Category");
        MapToCSV("report", report, headers);

        return  report;
    }

    private void toCSV(String fileName, List<NewsArticle> list, List<String> headers) {
        File file = new File(fileName+".csv");
        try (PrintWriter writer = new PrintWriter(file)){
            StringBuilder sb = new StringBuilder();

            // headers
            headers.stream().forEach(item -> sb.append(item).append(","));
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
            writer.write(sb.toString());
            //body
            StringBuilder sb1 = new StringBuilder();
            list.forEach(obj -> sb1.append(obj.getTitle()).append(",").append(obj.getDescription()).append(",").append(obj.getId()).append("\n"));
            writer.write(sb1.toString());
            //path file
            System.out.println("------------------------------------------------------");
            System.out.println("File written in " + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void MapToCSV(String fileName, Map map, List<String> headers){
        File file = new File(fileName+".csv");
        try (PrintWriter writer = new PrintWriter(file)){
            StringBuilder sb = new StringBuilder();

            // headers
            headers.stream().forEach(item -> sb.append(item).append(","));
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
            writer.write(sb.toString());
            //body
            StringBuilder sb1 = new StringBuilder();
            map.forEach((k,v) -> sb1.append(k).append(",").append(v).append("\n"));
            writer.write(sb1.toString());
            //path file
            System.out.println("------------------------------------------------------");
            System.out.println("File written in " + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
