package com.example.newsclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsArticle {

    private String id;
    private String category;
    private String title;
    private String description;
    private List<String> tags;
    private int weight;

    @Override
    public String toString() {
        return "NewsArticle{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", weight=" + weight +
                '}';
    }
}
