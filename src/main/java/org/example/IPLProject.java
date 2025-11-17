package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVReader.*;
import com.opencsv.exceptions.CsvException;



public class IPLProject implements ListOfScenarios{


    public static void main(String[] args ) {
        List<String[]> matches = new ArrayList<>();
        List<String[]> deliveries = new ArrayList<>();

        try
        {
            CSVReader matchesReader = new CSVReader(new FileReader("src/main/resources/matches.csv"
            ));
            CSVReader deliveriesReader = new CSVReader(new FileReader("src/main/resources/deliveries.csv"
            ));
            String line;
            deliveriesReader.readNext();
            matchesReader.readNext();
            matches = matchesReader.readAll();
            deliveries = deliveriesReader.readAll();
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
            if(!m.getWinner().isEmpty()) {
                hm.put(m.getWinner(), hm.getOrDefault(m.getWinner(), 0) + 1);
            }
        }

        for(Map.Entry<String,Integer> m: hm.entrySet()){
            System.out.println(m.getKey() + " -> " + m.getValue());
        }
    }

    public void etrasConcededPerYear(List<String[]> matches, List<String[]> deliveries, int year){
        HashSet<Integer> set = new HashSet<>();
        for(String[] str : matches){
            Match m = new Match(str);
            if(m.getSeason() == year){
                set.add(m.getId());
            }
        }

        HashMap<String , Integer> map = new HashMap<>();
        for(String[] str : deliveries){
            Deliveries d = new Deliveries(str);
            if(set.contains(d.getMatchId())){
                map.put(d.getBowlingTeam(), map.getOrDefault(d.getBowlingTeam(),0) + d.getExtraRuns());
            }
        }

        for(Map.Entry<String,Integer> m : map.entrySet()){
            System.out.println(m.getKey() + " , " + m.getValue());
        }
    }

    public  void topEconomicalBowler2015(){

    }
}
