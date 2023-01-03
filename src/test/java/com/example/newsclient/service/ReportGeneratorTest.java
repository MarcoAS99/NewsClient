package com.example.newsclient.service;

import com.example.newsclient.model.NewsArticle;
import com.example.newsclient.model.NewsCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportGeneratorTest {

    private ReportGenerator reportGenerator;

    @BeforeEach
    void setUp() {
        reportGenerator = new ReportGenerator();
    }

    @ParameterizedTest
    @MethodSource
    void reportMinimal(List<NewsArticle> newsArticleList, String expectedString) {
        assertEquals(expectedString, reportGenerator.reportMinimal(newsArticleList));
    }

    @ParameterizedTest
    @MethodSource
    void reportAll(List<NewsArticle> newsArticleList, String expectedString) {
        assertEquals(expectedString, reportGenerator.reportAll(newsArticleList));
    }

    @ParameterizedTest
    @MethodSource
    void reportByCategory(List<NewsArticle> newsArticleList, String expectedString) {
        assertEquals(expectedString, reportGenerator.reportByCategory(newsArticleList));
    }

    private static Stream<Arguments> reportMinimal(){
        return Stream.of(
                Arguments.arguments(Collections.EMPTY_LIST,
                        "Title,Category\n"),
                Arguments.arguments(List.of(new NewsArticle("1",String.valueOf(NewsCategory.SOCIAL),"A","",List.of("T"),1)),
                        "Title,Category\nA,SOCIAL\n"
                ),
                Arguments.arguments(List.of(new NewsArticle("1",String.valueOf(NewsCategory.SOCIAL),"A","",List.of("T"),1),
                                new NewsArticle("2",String.valueOf(NewsCategory.SPORT),"B","",List.of("T"),1)),
                        "Title,Category\nA,SOCIAL\nB,SPORT\n"
                ),
                Arguments.arguments(List.of(new NewsArticle("1",String.valueOf(NewsCategory.SOCIAL),"A","",List.of("T"),1),
                                new NewsArticle("2",String.valueOf(NewsCategory.SPORT),"B","",List.of("T"),1)),
                        "Title,Category\nA,SOCIAL\nB,SPORT\n"
                )
        );
    }

    private static Stream<Arguments> reportAll(){
        return Stream.of(
                Arguments.arguments(Collections.EMPTY_LIST,
                        "Title,Description,Id\n"),
                Arguments.arguments(List.of(new NewsArticle("1",String.valueOf(NewsCategory.SOCIAL),"A","",List.of("T"),1)),
                        "Title,Description,Id\nA,,1\n"
                ),
                Arguments.arguments(List.of(new NewsArticle("1",String.valueOf(NewsCategory.SOCIAL),"A","",List.of("T"),1),
                                new NewsArticle("2",String.valueOf(NewsCategory.SPORT),"B","Sport Article",List.of("T"),1)),
                        "Title,Description,Id\nA,,1\nB,Sport Article,2\n"
                )
        );
    }

    private static Stream<Arguments> reportByCategory(){
        return Stream.of(
                Arguments.arguments(Collections.EMPTY_LIST,
                        "Category,Value\n"),
                Arguments.arguments(List.of(new NewsArticle("1",String.valueOf(NewsCategory.SOCIAL),"A","",List.of("T"),1)),
                        "Category,Value\nSOCIAL,1\n"
                ),
                Arguments.arguments(List.of(new NewsArticle("1",String.valueOf(NewsCategory.SOCIAL),"A","",List.of("T"),1),
                                new NewsArticle("2",String.valueOf(NewsCategory.SPORT),"B","Sport Article",List.of("T"),1)),
                        "Category,Value\nSPORT,1\nSOCIAL,1\n"
                ),
                Arguments.arguments(List.of(new NewsArticle("1",String.valueOf(NewsCategory.SOCIAL),"A","",List.of("T"),1),
                                new NewsArticle("2",String.valueOf(NewsCategory.SPORT),"B","Sport Article",List.of("T"),1),
                                new NewsArticle("3",String.valueOf(NewsCategory.SOCIAL),"C","",List.of("T"),1)),
                        "Category,Value\nSPORT,1\nSOCIAL,2\n"
                )
        );
    }
}