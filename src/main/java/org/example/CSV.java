package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSV {

    //reading matches.csv
    public List<String[]> readMatches() {
        try (CSVReader matchesReader = new CSVReader(new FileReader("src/main/resources/matches.csv"))) {

            matchesReader.readNext();
            return matchesReader.readAll();

        } catch (Exception e) {
            throw new RuntimeException("Error reading matches.csv", e);
        }
    }

    //reading deliveries.csv
    public List<String[]> readDeliveries() {
        try (CSVReader deliveriesReader = new CSVReader(new FileReader("src/main/resources/deliveries.csv"))) {

            deliveriesReader.readNext();
            return deliveriesReader.readAll();

        } catch (Exception e) {
            throw new RuntimeException("Error reading deliveries.csv", e);
        }
    }
}
