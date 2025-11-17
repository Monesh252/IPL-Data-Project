package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVReader.*;
import com.opencsv.exceptions.CsvException;



public class IPLProject implements ListOfScenarios{

    static void main() {
        List<String[]> matches = new ArrayList<>();
        List<String[]> deliveries = new ArrayList<>();
        try
        {
            IPLProject ipl = new IPLProject();
            CSVReader matchesReader = new CSVReader(new FileReader("src/main/resources/matches.csv"
            ));
            CSVReader deliveriesReader = new CSVReader(new FileReader("src/main/resources/deliveries.csv"
            ));
            String line;
            matches = matchesReader.readAll();
            deliveries = deliveriesReader.readAll();
//            ipl.matchesPlayedPerYear(matches);
            ipl.matchesWonByAllTeams(matches);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public void matchesPlayedPerYear(List<String[]> matches){
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i = 1;i<matches.size();i++){
            Match m = new Match(matches.get(i));
            hm.put(m.getSeason(),hm.getOrDefault(m.getSeason(), 0)+1);
        }

        for(Map.Entry<Integer,Integer> m : hm.entrySet()){
            System.out.println(m.getKey() + " -> " + m.getValue());
        }
    }

    public  void matchesWonByAllTeams(List<String[]> matches){
        HashMap<String,Integer> hm = new HashMap<>();
        for(int i = 1;i<matches.size();i++){
            Match m = new Match(matches.get(i));
            if(m.getWinner()!=null) {
                hm.put(m.getWinner(), hm.getOrDefault(m.getWinner(), 0) + 1);
            }
        }

        for(Map.Entry<String,Integer> m: hm.entrySet()){
            System.out.println(m.getKey() + " -> " + m.getValue());
        }
    }

    public  void etrasConcededIn2016(){

    }

    public  void topEconomicalBowler2015(){

    }
}
