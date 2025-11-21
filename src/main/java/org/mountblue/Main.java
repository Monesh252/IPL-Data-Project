package org.mountblue;

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

    public static void main(String[] args){
        final String matchesFile = "src/main/resources/matches.csv";
        final String deliveriesFile = "src/main/resources/deliveries.csv";
        final List<Matches> matchesData = Collections.unmodifiableList(readMatches(matchesFile));
        final List<Deliveries> deliveriesData = Collections.unmodifiableList(readDeliveries(deliveriesFile));
        findPlayerWithHighestStrikeRateVsCSKInHyd(deliveriesData, matchesData);
    }

    public static List<Matches> readMatches(String file){
        List<Matches> matchList = new ArrayList<>();
        try(BufferedReader matches = new BufferedReader(new FileReader(file))) {
            String currentMatch;
            matches.readLine();
            while ((currentMatch = matches.readLine()) != null) {
                StringBuffer sb = new StringBuffer();
                boolean insideQuotes = false;
                ArrayList<String> fields = new ArrayList<>();
                for (char c : currentMatch.toCharArray()) {
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

                Matches match = new Matches();
                match.setId(Integer.parseInt(fields.get(MATCH_ID)));
                match.setSeason(Integer.parseInt(fields.get(SEASON)));
                match.setCity(fields.get(CITY));
                match.setDate(fields.get(DATE));
                match.setTeam1(fields.get(TEAM1));
                match.setTeam2(fields.get(TEAM2));
                match.setTossWinner(fields.get(TOSS_WINNER));
                match.setTossDecision(fields.get(TOSS_DECISION));
                match.setResult(fields.get(RESULT));
                match.setDl_applied(fields.get(DL_APPLIED));
                match.setWinner(fields.get(WINNER));
                match.setWin_by_runs(Integer.parseInt(fields.get(WIN_BY_RUNS)));
                match.setWin_by_wickets(Integer.parseInt(fields.get(WIN_BY_WICKETS)));
                match.setPlayer_of_match(fields.get(PLAYER_OF_MATCH));
                match.setVenue(fields.get(VENUE));
                match.setUmpire1(fields.get(UMPIRE1));
                match.setUmpire2(fields.get(UMPIRE2));
                match.setUmpire3(fields.get(UMPIRE3));
                matchList.add(match);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return matchList;
    }

    public static List<Deliveries> readDeliveries(String file){
        List<Deliveries> deliveryList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
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

                Deliveries delivery = new Deliveries();
                delivery.setMatchId(Integer.parseInt(fields.get(MATCH_ID)));
                delivery.setInning(Integer.parseInt(fields.get(INNING)));
                delivery.setBattingTeam(fields.get(BATTING_TEAM));
                delivery.setBowlingTeam(fields.get(BOWLING_TEAM));
                delivery.setOver(Integer.parseInt(fields.get(OVER)));
                delivery.setBall(Integer.parseInt(fields.get(BALL)));
                delivery.setBatsman(fields.get(BATSMAN));
                delivery.setNonStriker(fields.get(NON_STRIKER));
                delivery.setBowler(fields.get(BOWLER));
                delivery.setIsSuperOver(Integer.parseInt(fields.get(IS_SUPER_OVER)));
                delivery.setWideRuns(Integer.parseInt(fields.get(WIDE_RUNS)));
                delivery.setByeRuns(Integer.parseInt(fields.get(BYE_RUNS)));
                delivery.setLegByeRuns(Integer.parseInt(fields.get(LEG_BYE_RUNS)));
                delivery.setNoballRuns(Integer.parseInt(fields.get(NOBALL_RUNS)));
                delivery.setPenaltyRuns(Integer.parseInt(fields.get(PENALTY_RUNS)));
                delivery.setBatsmanRuns(Integer.parseInt(fields.get(BATSMAN_RUNS)));
                delivery.setExtraRuns(Integer.parseInt(fields.get(EXTRA_RUNS)));
                delivery.setTotalRuns(Integer.parseInt(fields.get(TOTAL_RUNS)));
                delivery.setPlayerDismissed(fields.get(PLAYER_DISMISSED));
                delivery.setDismissalKind(fields.get(DISMISSAL_KIND));
                delivery.setFielder(fields.get(FIELDER));
                deliveryList.add(delivery);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return deliveryList;
    }

    public static void findPlayerWithHighestStrikeRateVsCSKInHyd(List<Deliveries> deliveries, List<Matches> matches) {
        for(int startYear = 2008; startYear <= 2016; startYear++) {
            Set<Integer> matchIds = new HashSet<>();

            for (Matches match : matches) {
                if (match.getCity().toLowerCase().contains("chennai") &&
                        ("Chennai Super Kings".equals(match.getTeam2())) && match.getSeason() == startYear) {
                    matchIds.add(match.getId());
                }
            }

            Map<String, Integer> totalRuns = new HashMap<>();
            Map<String, Integer> totalBalls = new HashMap<>();

            for (Deliveries delivery : deliveries) {
                if (!matchIds.contains(delivery.getMatchId()))
                    continue;

                String batsman = delivery.getBatsman();
                int batsmanRuns = delivery.getBatsmanRuns();
                int extraRuns = delivery.getExtraRuns();

                totalRuns.put(batsman, totalRuns.getOrDefault(batsman, 0) + batsmanRuns);

                if (extraRuns == 0) {
                    totalBalls.put(batsman, totalBalls.getOrDefault(batsman, 0) + 1);
                }
            }

            Map<String, Double> strikeRate = new HashMap<>();

            for (String batsman : totalRuns.keySet()) {
                if (totalBalls.containsKey(batsman)) {
                    double runs = totalRuns.get(batsman);
                    double balls = totalBalls.get(batsman);
                    double strikeRateCalculation = (runs / balls) * 100;
                    strikeRate.put(batsman, strikeRateCalculation);
                }
            }

            if (!totalRuns.isEmpty()) {
                System.out.println(startYear);
                Map.Entry<String, Double> top = Collections.max(strikeRate.entrySet(), Map.Entry.comparingByValue());
                System.out.printf("%-18s -> %3.2f\n", top.getKey(), top.getValue());
            }
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


    public static void findMatchesPlayedPerYear(List<Matches> matches){
        Map<Integer, Integer> totalMatches = new TreeMap<>();

        for(Matches match : matches){
            int season =match.getSeason();
            totalMatches.put(season, totalMatches.getOrDefault(season, 0)+1);
        }

        for(Map.Entry<Integer, Integer> match : totalMatches.entrySet()){
                System.out.println(match.getKey() + " -> " + match.getValue());
        }
    }

    public static void findMatchesWonByAllTeams(List<Matches> matches){
        Map<String, Integer> totalWins = new HashMap<>();

        for(Matches match : matches){
            String winner = match.getWinner();
            if(!match.getWinner().isEmpty()) {
                totalWins.put(winner, totalWins.getOrDefault(winner, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(totalWins.entrySet());
        list.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
        totalWins = new HashMap<>();

        for(Map.Entry<String, Integer> value : list){
            totalWins.put(value.getKey(),value.getValue());
        }

        for(Map.Entry<String, Integer> entry: totalWins.entrySet()){
            System.out.printf("%-28s -> %-3d wins\n",entry.getKey(),entry.getValue());
        }
    }

    public static void findExtrasConcededByATeamPerYear(List<Matches> matches, List<Deliveries> deliveries, int year){
        Set<Integer> matchId = matchId(matches,year);
        HashMap<String, Integer> extras = new HashMap<>();

        for(Deliveries delivery : deliveries){
            int matchNumber = delivery.getMatchId();
            int isSuperOver = delivery.getIsSuperOver();
            String bowlingTeam = delivery.getBowlingTeam();
            int extraRuns = delivery.getExtraRuns();

            if(matchId.contains(matchNumber) && isSuperOver == 0){
                extras.put(bowlingTeam, extras.getOrDefault(bowlingTeam,0) + extraRuns);
            }
        }

        for(Map.Entry<String, Integer> entry : extras.entrySet()){
            System.out.printf("%-28s -> %d Extras\n", entry.getKey(), entry.getValue());
        }
    }

    public static void findTopEconomicalBowler(List<Matches> matches, List<Deliveries> deliveries, int year){
        Set<Integer> matchId = matchId(matches,year);
        Map<String, Integer> totalBalls = new HashMap<>();
        Map<String, Integer> totalRuns = new HashMap<>();

        for(Deliveries delivery : deliveries){
            int matchNumber = delivery.getMatchId();
            String bowler = delivery.getBowler();
            int totalRunsInDelivery = delivery.getTotalRuns();
            int wideRuns = delivery.getWideRuns();
            int noballRuns = delivery.getNoballRuns();

            if(matchId.contains(matchNumber)){
                totalRuns.put(bowler,totalRuns.getOrDefault(bowler,0) + totalRunsInDelivery);
                if(noballRuns == 0 && wideRuns == 0){
                    totalBalls.put(delivery.getBowler(),totalBalls.getOrDefault(delivery.getBowler(),0) + 1);
                }
            }
        }

        Map<String, Double> economy = new HashMap<>();
        for (String bowler : totalRuns.keySet()) {
            if (totalBalls.containsKey(bowler)) {
                int runs = totalRuns.get(bowler);
                int balls = totalBalls.get(bowler);
                double economyCalculation = (runs * 6.0) / balls;
                economy.put(bowler,economyCalculation);
            }
        }

        List<Map.Entry<String, Double>> economicalBowler = new ArrayList<>(economy.entrySet());
        economicalBowler.sort(Map.Entry.<String,Double>comparingByValue());

        for(Map.Entry<String, Double> entry : economicalBowler){
            System.out.printf("%-18s -> %-4.2f \n",entry.getKey(),entry.getValue());
        }
    }

    public static void findHighestRunGetterInAYear(List<Matches> matches, List<Deliveries> deliveries, int year){
        Set<Integer> matchId = matchId(matches, year);
        HashMap<String, Integer> totalRuns = new HashMap<>();

       for (Deliveries delivery : deliveries) {
           String batsman = delivery.getBatsman();
           int totalRunsInDelivery = delivery.getBatsmanRuns();
           if (matchId.contains(delivery.getMatchId())) {
               totalRuns.put(delivery.getBatsman(), totalRuns.getOrDefault(batsman, 0)
                       + totalRunsInDelivery);
           }
       }

       if(!totalRuns.isEmpty()){
           Map.Entry<String, Integer> top = Collections.max(totalRuns.entrySet(), Map.Entry.comparingByValue());
           System.out.printf("%-18s -> %4d\n", top.getKey(), top.getValue());
       }
    }

    public static void findHighestStrickeRateInDeathOvers(List<Deliveries> deliveries){
        Map<String, Integer> totalBalls = new HashMap<>();
        Map<String, Integer> totalRuns = new HashMap<>();

        for(Deliveries delivery : deliveries){
            String batsman = delivery.getBatsman();
            int runsInDelivey = delivery.getTotalRuns();
            int extraRuns = delivery.getExtraRuns();
            int batsmanRuns = delivery.getBatsmanRuns();
            if(delivery.getOver() >= 16){
                totalRuns.put(batsman,totalRuns.getOrDefault(batsman,0) + batsmanRuns);
                if(extraRuns == 0){
                    totalBalls.put(batsman,totalBalls.getOrDefault(batsman,0) + 1);
                }
            }
        }

        Map<String, Double> strikeRate = new HashMap<>();

        for (String batsman : totalRuns.keySet()) {
            if (totalBalls.containsKey(batsman)) {
                double runs = totalRuns.get(batsman);
                double balls = totalBalls.get(batsman);
                double strikeRateCalculation = (runs / balls) * 100;
                strikeRate.put(batsman,strikeRateCalculation);
            }
        }

        List<Map.Entry<String, Double>> highestStrikeRate = new ArrayList<>(strikeRate.entrySet());
        highestStrikeRate.sort(Map.Entry.<String,Double>comparingByValue().reversed());

        for(Map.Entry<String,Double> entry : highestStrikeRate){
            if(entry.getValue() != 0.00) {
                System.out.printf("%-20s -> %-3.2f\n", entry.getKey(), entry.getValue());
            }
        }
    }
}