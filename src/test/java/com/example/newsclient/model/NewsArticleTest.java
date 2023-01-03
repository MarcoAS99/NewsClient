package com.example.newsclient.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NewsArticleTest {

    @Test
    public void equalsContract(){
        NewsArticle newsArticle = mock(NewsArticle.class);

        assertEquals(newsArticle,newsArticle);
    }

    @Test
    public void toStringTest(){
        NewsArticle newsArticle = mock(NewsArticle.class);
        String toString = "NewsArticle{" +
                "id='" + newsArticle.getId() + '\'' +
                ", category='" + newsArticle.getCategory() + '\'' +
                ", title='" + newsArticle.getTitle() + '\'' +
                ", description='" + newsArticle.getDescription() + '\'' +
                ", tags=" + newsArticle.getTags() +
                ", weight=" + newsArticle.getWeight() +
                '}';
        when(newsArticle.toString()).thenReturn(toString);

        assertEquals(newsArticle.toString(),toString);
    }
}
