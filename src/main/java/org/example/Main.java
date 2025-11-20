package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static final int MATCH_ID = 0;
    public static final int SEASON = 1;
    public static final int CITY = 2;
    public static final int DATE = 3;
    public static final int TEAM1 = 4;
    public static final int TEAM2 = 5;
    public static final int TOSS_WINNER = 6;
    public static final int TOSS_DECISION = 7;
    public static final int RESULT = 8;
    public static final int DL_APPLIED = 9;
    public static final int WINNER = 10;
    public static final int WIN_BY_RUNS = 11;
    public static final int WIN_BY_WICKETS = 12;
    public static final int PLAYER_OF_MATCH = 13;
    public static final int VENUE = 14;
    public static final int UMPIRE1 = 15;
    public static final int UMPIRE2 = 16;
    public static final int UMPIRE3 = 17;
    public static final int INNING = 1;
    public static final int BATTING_TEAM = 2;
    public static final int BOWLING_TEAM = 3;
    public static final int OVER = 4;
    public static final int BALL = 5;
    public static final int BATSMAN = 6;
    public static final int NON_STRIKER = 7;
    public static final int BOWLER = 8;
    public static final int IS_SUPER_OVER = 9;
    public static final int WIDE_RUNS = 10;
    public static final int BYE_RUNS = 11;
    public static final int LEG_BYE_RUNS = 12;
    public static final int NOBALL_RUNS = 13;
    public static final int PENALTY_RUNS = 14;
    public static final int BATSMAN_RUNS = 15;
    public static final int EXTRA_RUNS = 16;
    public static final int TOTAL_RUNS = 17;
    public static final int PLAYER_DISMISSED = 18;
    public static final int DISMISSAL_KIND = 19;
    public static final int FIELDER = 20;

    public static void main(String[] args) throws IOException {
        final String matchesFile = "src/main/resources/matches.csv";
        final String deliveriesFile = "src/main/resources/deliveries.csv";
        final List<Matches> matchesData = Collections.unmodifiableList(readMatches(matchesFile));
        final List<Deliveries> deliveriesData = Collections.unmodifiableList(readDeliveries(deliveriesFile));
        findTopEconomicalBowler(matchesData,deliveriesData,2015);
    }

    public static List<Matches> readMatches(String file) throws IOException {
        List<Matches> matchList = new ArrayList<>();
        BufferedReader matches = new BufferedReader(new FileReader(file));
        String line;
        matches.readLine();
        while ((line = matches.readLine()) != null) {
            StringBuffer sb = new StringBuffer();
            boolean insideQuotes = false;
            ArrayList<String> fields = new ArrayList<>();

            for (char c : line.toCharArray()) {
                if (c == '"') {
                    insideQuotes = !insideQuotes;
                } else if (c == ',' && !insideQuotes) {
                    fields.add(sb.toString().trim());
                    sb.setLength(0);
                } else {
                    sb.append(c);
                }
            }
            fields.add(sb.toString().trim());
            String[] data = fields.toArray(new String[0]);

            Matches match = new Matches();
            match.setId(Integer.parseInt(data[MATCH_ID]));
            match.setSeason(Integer.parseInt(data[SEASON]));
            match.setCity(data[CITY]);
            match.setDate(data[DATE]);
            match.setTeam1(data[TEAM1]);
            match.setTeam2(data[TEAM2]);
            match.setTossWinner(data[TOSS_WINNER]);
            match.setTossDecision(data[TOSS_DECISION]);
            match.setResult(data[RESULT]);
            match.setDl_applied(data[DL_APPLIED]);
            match.setWinner(data[WINNER]);
            match.setWin_by_runs(Integer.parseInt(data[WIN_BY_RUNS]));
            match.setWin_by_wickets(Integer.parseInt(data[WIN_BY_WICKETS]));
            match.setPlayer_of_match(data[PLAYER_OF_MATCH]);
            match.setVenue(data[VENUE]);
            match.setUmpire1(data[UMPIRE1]);
            match.setUmpire2(data[UMPIRE2]);
            match.setUmpire3(data[UMPIRE3]);

            matchList.add(match);
        }
        return matchList;
    }

    public static List<Deliveries> readDeliveries(String file) throws IOException {
        List<Deliveries> deliveryList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        br.readLine();

        while ((line = br.readLine()) != null) {
            StringBuffer sb = new StringBuffer();
            boolean insideQuotes = false;
            ArrayList<String> fields = new ArrayList<>();

            for (char c : line.toCharArray()) {
                if (c == '"') {
                    insideQuotes = !insideQuotes;
                } else if (c == ',' && !insideQuotes) {
                    fields.add(sb.toString().trim());
                    sb.setLength(0);
                } else {
                    sb.append(c);
                }
            }
            fields.add(sb.toString().trim());
            String[] data = fields.toArray(new String[0]);

            Deliveries delivery = new Deliveries();
            delivery.setMatchId(Integer.parseInt(data[MATCH_ID]));
            delivery.setInning(Integer.parseInt(data[INNING]));
            delivery.setBattingTeam(data[BATTING_TEAM]);
            delivery.setBowlingTeam(data[BOWLING_TEAM]);
            delivery.setOver(Integer.parseInt(data[OVER]));
            delivery.setBall(Integer.parseInt(data[BALL]));
            delivery.setBatsman(data[BATSMAN]);
            delivery.setNonStriker(data[NON_STRIKER]);
            delivery.setBowler(data[BOWLER]);
            delivery.setIsSuperOver(Integer.parseInt(data[IS_SUPER_OVER]));
            delivery.setWideRuns(Integer.parseInt(data[WIDE_RUNS]));
            delivery.setByeRuns(Integer.parseInt(data[BYE_RUNS]));
            delivery.setLegByeRuns(Integer.parseInt(data[LEG_BYE_RUNS]));
            delivery.setNoballRuns(Integer.parseInt(data[NOBALL_RUNS]));
            delivery.setPenaltyRuns(Integer.parseInt(data[PENALTY_RUNS]));
            delivery.setBatsmanRuns(Integer.parseInt(data[BATSMAN_RUNS]));
            delivery.setExtraRuns(Integer.parseInt(data[EXTRA_RUNS]));
            delivery.setTotalRuns(Integer.parseInt(data[TOTAL_RUNS]));
            delivery.setPlayerDismissed(data[PLAYER_DISMISSED]);
            delivery.setDismissalKind(data[DISMISSAL_KIND]);
            delivery.setFielder(data[FIELDER]);

            deliveryList.add(delivery);
        }
        return deliveryList;
    }

    public static void findMatchesPlayedPerYear(List<Matches> matches){
        Map<Integer,Integer> totalMatches = new TreeMap<>();
        for(Matches match : matches){
            totalMatches.put(match.getSeason(), totalMatches.getOrDefault(match.getSeason(), 0)+1);
        }

        for(Map.Entry<Integer,Integer> m : totalMatches.entrySet()){
                System.out.println(m.getKey() + " -> " + m.getValue());
        }
    }

    public static void findMatchesWonByAllTeams(List<Matches> matches){
        Map<String,Integer> totalWins = new HashMap<>();

        for(Matches match : matches){
            if(!match.getWinner().isEmpty()) {//if winner is empty it is a no result match so we are avoiding that
                totalWins.put(match.getWinner(), totalWins.getOrDefault(match.getWinner(), 0) + 1);
            }
        }

        List<Map.Entry<String,Integer>> list = new ArrayList<>(totalWins.entrySet());
        list.sort(Map.Entry.<String,Integer>comparingByValue().reversed());
        totalWins = new HashMap<>();

        for(Map.Entry<String,Integer> value : list){
            totalWins.put(value.getKey(),value.getValue());
        }

        for(Map.Entry<String,Integer> entry: totalWins.entrySet()){
            System.out.printf("%-28s -> %-3d wins\n",entry.getKey(),entry.getValue());
        }
    }

    public static void findExtrasConcededPerYear(List<Matches> matches, List<Deliveries> deliveries, int year){
        Set<Integer> matchId = matchId(matches,year);

        HashMap<String , Integer> extras = new HashMap<>();
        for(Deliveries delivery : deliveries){
            if(matchId.contains(delivery.getMatchId()) && delivery.getIsSuperOver() == 0){
                extras.put(delivery.getBowlingTeam(), extras.getOrDefault(delivery.getBowlingTeam(),0) + delivery.getExtraRuns());
            }
        }

        for(Map.Entry<String,Integer> entry : extras.entrySet()){
            System.out.printf("%-28s -> %d Extras\n", entry.getKey(), entry.getValue());
        }
    }

    public static void findTopEconomicalBowler(List<Matches> matches, List<Deliveries> deliveries, int year){
        Set<Integer> matchId = matchId(matches,year);

        HashMap<String, Integer> totalBalls = new HashMap<>();
        HashMap<String, Integer> totalRuns = new HashMap<>();

        for(Deliveries delivery : deliveries){
            if(matchId.contains(delivery.getMatchId())){
                totalRuns.put(delivery.getBowler(),totalRuns.getOrDefault(delivery.getBowler(),0)+delivery.getTotalRuns());
                if(delivery.getNoballRuns() == 0 && delivery.getWideRuns() == 0){
                    totalBalls.put(delivery.getBowler(),totalBalls.getOrDefault(delivery.getBowler(),0) + 1);
                }
            }
        }

        Map<String,Double> economy = new HashMap<>();
        for (String bowler : totalRuns.keySet()) {
            if (totalBalls.containsKey(bowler)) {
                int runs = totalRuns.get(bowler);
                int balls = totalBalls.get(bowler);
                double economyCalculation = (runs * 6.0) / balls;
                economy.put(bowler,economyCalculation);
            }
        }

        List<Map.Entry<String,Double>> list = new ArrayList<>(economy.entrySet());
        list.sort(Map.Entry.comparingByValue());
        economy = new LinkedHashMap<>();
        for(Map.Entry<String,Double> run : list){
            economy.put(run.getKey(),run.getValue());
        }

        for(Map.Entry<String,Double> entry : economy.entrySet()){
            System.out.printf("%-18s -> %-4.2f \n",entry.getKey(),entry.getValue());
        }
    }

    public static void findHighestRunGetterInAYear(List<Matches> matches, List<Deliveries> deliveries, int year){
        Set<Integer> matchId = matchId(matches,year);

       HashMap<String, Integer> totalRuns = new HashMap<>();
       for (Deliveries delivery : deliveries) {
           if (matchId.contains(delivery.getMatchId())) {
               totalRuns.put(delivery.getBatsman(), totalRuns.getOrDefault(delivery.getBatsman(), 0) + delivery.getTotalRuns());
           }
       }

       List<Map.Entry<String,Integer>> list = new ArrayList<>(totalRuns.entrySet());
       list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
       totalRuns = new LinkedHashMap<>();
       for(Map.Entry<String,Integer> run : list){
           totalRuns.put(run.getKey(),run.getValue());
       }

       if(!totalRuns.isEmpty()){
           Map.Entry<String,Integer> top = totalRuns.entrySet().iterator().next();
           System.out.printf("%-18s -> %4d\n", top.getKey(), top.getValue());
       }
    }

    public static Set<Integer> matchId(List<Matches> matches, int year){
        Set<Integer> matchId = new HashSet<>();
        for(Matches match : matches){
            if(match.getSeason() == year){
                matchId.add(match.getId());
            }
        }
        return matchId;
    }
}

