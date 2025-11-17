package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReader.*;
import com.opencsv.exceptions.CsvException;

public class IPLProject implements ListOfScenarios{

    static void main() {
        try
        {
            CSVReader matchesReader = new CSVReader(new FileReader("src/main/resources/matches.csv"
            ));
            CSVReader deliveriesReader = new CSVReader(new FileReader("src/main/resources/deliveries.csv"
            ));
            List<String[]> matches = matchesReader.readAll();
            List<String[]> deliveries = deliveriesReader.readAll();
            System.out.println(Arrays.asList(matches.get(0)));
//            matchesPerYear(matches);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

}
