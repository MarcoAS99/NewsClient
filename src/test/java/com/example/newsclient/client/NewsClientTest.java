package com.example.newsclient.client;

import com.example.newsclient.model.NewsArticle;
import com.example.newsclient.model.NewsCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewsClientTest {

    @Mock
    private NewsClient newsClient;

    @Test
    void CanGetNews(){
        when(newsClient.getNews()).thenReturn(Collections.EMPTY_LIST);
        assertEquals(Collections.EMPTY_LIST, newsClient.getNews());
    }

    @ParameterizedTest
    @MethodSource
    void getNewsByCategoryTests(List<NewsArticle> newsArticlesList, String category, List<NewsArticle> expectedArticlesList){
        when(newsClient.getNewsByCategory(newsArticlesList, category)).thenReturn(expectedArticlesList);
        assertEquals(expectedArticlesList,newsClient.getNewsByCategory(newsArticlesList,category));
    }

    private static Stream<Arguments> getNewsByCategoryTests(){
        return Stream.of(
                Arguments.arguments(Collections.EMPTY_LIST,
                        String.valueOf(NewsCategory.SOCIAL),
                        Collections.EMPTY_LIST),
                Arguments.arguments(List.of(new NewsArticle("1",String.valueOf(NewsCategory.SOCIAL),"A","",List.of("T"),1)),
                        String.valueOf(NewsCategory.SOCIAL),
                        List.of(new NewsArticle("1",String.valueOf(NewsCategory.SOCIAL),"A","",List.of("T"),1))
                        ),
                Arguments.arguments(List.of(new NewsArticle("1",String.valueOf(NewsCategory.SOCIAL),"A","",List.of("T"),1)),
                        String.valueOf(NewsCategory.SPORT),
                        Collections.EMPTY_LIST
                )
        );
    }

}