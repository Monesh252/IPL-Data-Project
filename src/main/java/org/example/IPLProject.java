package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVReader.*;
import com.opencsv.exceptions.CsvException;



public class IPLProject  implements ListOfScenarios{

    public static void main(String[] args ) {
        CSV csv = new CSV();
        Scanner sc = new Scanner(System.in);
        List<String[]> deliveries = csv.readDeliveries();
        List<String[]> matches = csv.readMatches();
        IPLProject ipl = new IPLProject();

        String choice;
        do {
            System.out.println("1 : Matches played per year");
            System.out.println("2 : Matches won by all teams");
            System.out.println("3 : Extras conceded per year");
            System.out.println("4 : Top economical bowler");
            System.out.println("5 : Highest run getter in a year");
            System.out.println("Type 'End' to exit");
            System.out.println("Enter the number of the scenario you need to know");

            choice = sc.nextLine();  // Always take string

            switch (choice) {
                case "1":
                    ipl.matchesPlayedPerYear(matches);
                    break;

                case "2":
                    ipl.matchesWonByAllTeams(matches);
                    break;

                case "3":
                    System.out.println("Enter the year:");
                    int year = Integer.parseInt(sc.nextLine()); // important
                    ipl.extrasConcededPerYear(matches, deliveries, year);
                    break;

                case "4":
                    System.out.println("Enter the year:");
                    int year2 = Integer.parseInt(sc.nextLine());
                    ipl.topEconomicalBowler(matches, deliveries, year2);
                    break;

                case "5":
                    System.out.println("Enter the year:");
                    int year3 = Integer.parseInt(sc.nextLine());
                    ipl.highestRunGetterInEachYear(matches, deliveries, year3);
                    break;

                case "End":
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Enter a valid option.");
            }

        } while (!choice.equals("End"));

        sc.close();
    }


        public void matchesPlayedPerYear(List<String[]> matches){
        //Hashmap to store the season in key and count the matches in value
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i = 0;i<matches.size();i++){
            Match m = new Match(matches.get(i));
            hm.put(m.getSeason(),hm.getOrDefault(m.getSeason(), 0)+1);
        }

        for(Map.Entry<Integer,Integer> m : hm.entrySet()){
                System.out.println(m.getKey() + " -> " + m.getValue());

        }
    }

    public  void matchesWonByAllTeams(List<String[]> matches){
        //Hashmap to store the winner in the key and number of matches they won in value
        HashMap<String,Integer> hm = new HashMap<>();
        for(int i = 1;i<matches.size();i++){
            Match m = new Match(matches.get(i));
            if(!m.getWinner().isEmpty()) {//if winner is empty it is a no result match so we are avoiding that
                hm.put(m.getWinner(), hm.getOrDefault(m.getWinner(), 0) + 1);
            }
        }

        for(Map.Entry<String,Integer> m: hm.entrySet()){
            System.out.println(m.getKey() + " -> " + m.getValue());
        }
    }

    public void extrasConcededPerYear(List<String[]> matches, List<String[]> deliveries, int year){
        //Set to store the match id present in the particular year
        //We use set instead of list because set will do direct access with the help of hashcode
        //whereas list will check one by one
        HashSet<Integer> set = new HashSet<>();
        for(String[] str : matches){
            Match m = new Match(str);
            if(m.getSeason() == year){
                set.add(m.getId());
            }
        }

        //Using hashmap to store the bowling team in the key and counting extra runs in the value
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

    public  void topEconomicalBowler(List<String[]> matches, List<String[]> deliveries, int year){
        //Set to store the match id present in the particular year
        //We use set instead of list because set will do direct access with the help of hashcode
        //whereas list will check one by one
        Set<Integer> set = new HashSet<>();
        for(String[] str : matches){
            Match m = new Match(str);
            if(m.getSeason() == year){
                set.add(m.getId());
            }
        }

        HashMap<String, Integer> totalBalls = new HashMap<>();//Storing the player name and total number of balls he bowled
        HashMap<String, Integer> totalRuns = new HashMap<>();//Storing the player name and total runs he conceded
        for(String[] str : deliveries){
            Deliveries d = new Deliveries(str);
            if(set.contains(d.getMatchId())){
                totalRuns.put(d.getBowler(),totalRuns.getOrDefault(d.getBowler(),0)+d.getTotalRuns());
                if(d.getNoballRuns() == 0 && d.getWideRuns() == 0){
                    totalBalls.put(d.getBowler(),totalBalls.getOrDefault(d.getBowler(),0) + 1);
                }
            }
        }

        //Calculating the economy of the bowler and storing it in the runRate map
        Map<String,Double> runRate = new TreeMap<>();
        for (String bowler : totalRuns.keySet()) {
            if (totalBalls.containsKey(bowler)) {
                int runs = totalRuns.get(bowler);
                int balls = totalBalls.get(bowler);
                double economy = (runs * 6.0) / balls;
                runRate.put(bowler,economy);
            }
        }

        //Sroting the map
        List<Map.Entry<String,Double>> list = new ArrayList<>(runRate.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<String,Double> sortedEconomy = new LinkedHashMap<>();
        for(Map.Entry<String,Double> run : list){
            sortedEconomy.put(run.getKey(),run.getValue());
        }

        for(Map.Entry<String,Double> sorted : sortedEconomy.entrySet()){
            System.out.println(sorted.getKey() + " -> " + String.format("%.2f" ,sorted.getValue()));
        }
    }

    //Own Scenario
    public  void highestRunGetterInEachYear(List<String[]> matches, List<String[]> deliveries, int year) {
        //Set to store the match id present in the particular year
        //We use set instead of list because set will do direct access with the help of hashcode
        //whereas list will check one by one
       Set<Integer> set = new HashSet<>();
       for (String[] str : matches) {
           Match m = new Match(str);
           if (m.getSeason() == year) {
               set.add(m.getId());
           }
       }

       //Storing total runs scored by the batsman
       HashMap<String, Integer> totalRuns = new HashMap<>();
       for (String[] str : deliveries) {
           Deliveries d = new Deliveries(str);
           if (set.contains(d.getMatchId())) {
               totalRuns.put(d.getBatsman(), totalRuns.getOrDefault(d.getBatsman(), 0) + d.getTotalRuns());
           }
       }

       //Sorting the map
       List<Map.Entry<String,Integer>> list = new ArrayList<>(totalRuns.entrySet());
       list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
       Map<String,Integer> sortedEconomy = new LinkedHashMap<>();
       for(Map.Entry<String,Integer> run : list){
           sortedEconomy.put(run.getKey(),run.getValue());
       }

       int n = 0;
       for(Map.Entry<String,Integer> m : sortedEconomy.entrySet()){
           if(n<=0) {
               System.out.println(m.getKey() + " -> " + m.getValue());
               n++;
           }
       }
    }
}
