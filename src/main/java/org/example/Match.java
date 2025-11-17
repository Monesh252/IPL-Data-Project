package org.example;

public class Match {

    private int id;
    private int season;
    private String city;
    private String date;
    private String team1;
    private String team2;
    private String tossWinner;
    private String tossDecision;
    private String result;
    private String dl_applied;
    private String winner;
    private int win_by_runs;
    private int win_by_wickets;
    private String player_of_match;
    private String venue;
    private String umpire1;
    private String umpire2;
    private String umpire3;


    public Match(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.season = Integer.parseInt(data[1]);
        this.city = data[2];
        this.date = data[3];
        this.team1 = data[4];
        this.team2 = data[5];
        this.tossWinner = data[6];
        this.tossDecision = data[7];
        this.result = data[8];
        this.dl_applied = data[9];
        this.winner = data[10];
        this.win_by_runs = Integer.parseInt(data[11]);
        this.win_by_wickets = Integer.parseInt(data[12]);
        this.player_of_match = data[13];
        this.venue = data[14];
        this.umpire1 = data[15];
        this.umpire2 = data[16];
        this.umpire3 = data[17];
    }

    public int getId() {
        return id;
    }

    public int getSeason() {
        return season;
    }

    public String getCity() {
        return city;
    }

    public String getDl_applied() {
        return dl_applied;
    }

    public int getWin_by_runs() {
        return win_by_runs;
    }

    public int getWin_by_wickets() {
        return win_by_wickets;
    }

    public String getPlayer_of_match() {
        return player_of_match;
    }

    public String getVenue() {
        return venue;
    }

    public String getUmpire1() {
        return umpire1;
    }

    public String getUmpire2() {
        return umpire2;
    }

    public String getUmpire3() {
        return umpire3;
    }

    public String getDate() {
        return date;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public String getTossDecision() {
        return tossDecision;
    }

    public String getResult() {
        return result;
    }

    public String getWinner() {
        return winner;
    }
}

