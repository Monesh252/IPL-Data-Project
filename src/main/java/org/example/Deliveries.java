package org.example;

public class Deliveries {

    private String matchId;
    private int inning;
    private String battingTeam;
    private String bowlingTeam;
    private int over;
    private int ball;
    private String batsman;
    private String nonStriker;
    private String bowler;
    private  int isSuperOver;
    private int wideRuns;
    private int byeRuns;
    private int legByeRuns;
    private int noballRuns;
    private int penaltyRuns;
    private int batsmanRuns;
    private int extraRuns;
    private int totalRuns;
    private String playerDismissed;
    private String dismissalKind;
    private String fielder;

    public void Delivery(String[] data) {
        this.matchId = data[0];
        this.inning = Integer.parseInt(data[1]);
        this.battingTeam = data[2];
        this.bowlingTeam = data[3];
        this.over = Integer.parseInt(data[4]);
        this.ball = Integer.parseInt(data[5]);
        this.batsman = data[6];
        this.nonStriker = data[7];
        this.bowler = data[8];
        this.isSuperOver = Integer.parseInt(data[9]);
        this.wideRuns = Integer.parseInt(data[10]);
        this.byeRuns = Integer.parseInt(data[11]);
        this.legByeRuns = Integer.parseInt(data[12]);
        this.noballRuns = Integer.parseInt(data[13]);
        this.penaltyRuns = Integer.parseInt(data[14]);
        this.batsmanRuns = Integer.parseInt(data[15]);
        this.extraRuns = Integer.parseInt(data[16]);
        this.totalRuns = Integer.parseInt(data[17]);
        this.playerDismissed = data[18];
        this.dismissalKind = data[19];
        this.fielder = data[20];
    }

    public String getMatchId() {
        return matchId;
    }

    public int getInning() {
        return inning;
    }

    public String getBattingTeam() {
        return battingTeam;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
    }

    public int getOver() {
        return over;
    }

    public int getBall() {
        return ball;
    }

    public String getBatsman() {
        return batsman;
    }

    public String getNonStriker() {
        return nonStriker;
    }

    public String getBowler() {
        return bowler;
    }

    public int getIsSuperOver() {
        return isSuperOver;
    }

    public int getWideRuns() {
        return wideRuns;
    }

    public int getByeRuns() {
        return byeRuns;
    }

    public int getLegByeRuns() {
        return legByeRuns;
    }

    public int getNoballRuns() {
        return noballRuns;
    }

    public int getPenaltyRuns() {
        return penaltyRuns;
    }

    public int getBatsmanRuns() {
        return batsmanRuns;
    }

    public int getExtraRuns() {
        return extraRuns;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public String getPlayerDismissed() {
        return playerDismissed;
    }

    public String getDismissalKind() {
        return dismissalKind;
    }

    public String getFielder() {
        return fielder;
    }
}

