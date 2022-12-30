package com.example.newsclient;

import com.example.newsclient.client.NewsClient;
import com.example.newsclient.model.NewsArticle;
import com.example.newsclient.model.NewsCategory;
import com.example.newsclient.service.ReportGenerator;
import com.example.newsclient.service.ReportService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class NewsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsClientApplication.class, args);

        NewsClient client = new NewsClient();

        List<NewsArticle> news = client.getNews();
        client.getNewsByCategory(news, String.valueOf(NewsCategory.SOCIAL));

        ReportGenerator reportGenerator = new ReportGenerator();
        String reportAll = reportGenerator.reportAll(news);
        String reportByCategory = reportGenerator.reportByCategory(news);
        String reportMinimal = reportGenerator.reportMinimal(news);

        ReportService reportService = new ReportService();
        reportService.reportToCSV("reportAll", reportAll);
        reportService.reportToCSV("reportByCategory", reportByCategory);
        reportService.reportToCSV("reportMinimal", reportMinimal);
    }

}
