package com.example.newsclient.service;

import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Slf4j
public class ReportService {

    public void reportToCSV(String fileName, String report){
        File file = new File(fileName + ".csv");
        try(PrintWriter writer = new PrintWriter(file)){
            writer.write(report);
            log.info("File written in {}",file.getAbsolutePath());
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
