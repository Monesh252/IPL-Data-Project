package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReader.*;
import com.opencsv.exceptions.CsvException;



public class IPLProject implements ListOfScenarios{

    static void main() {
        List<String[]> matches = new ArrayList<>();
        List<String[]> deliveries = new ArrayList<>();
        try
        {
            CSVReader matchesReader = new CSVReader(new FileReader("src/main/resources/matches.csv"
            ));
            CSVReader deliveriesReader = new CSVReader(new FileReader("src/main/resources/deliveries.csv"
            ));
            String line;
            matches = matchesReader.readAll();
            deliveries = deliveriesReader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public void matchesPlayedPerYear(List<String[]> matches){

    }

    public  void matchesWonByAllTeams(){

    }

    public  void etrasConcededIn2016(){

    }

    public  void topEconomicalBowler2015(){

    }
}
