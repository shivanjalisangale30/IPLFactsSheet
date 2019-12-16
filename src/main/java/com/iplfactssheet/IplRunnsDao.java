package com.iplfactssheet;

public class IplRunnsDao {

    public String player;
    public int matchs;
    public int innings;
    public int runs;
    public double average;
    public double strikingRates;
    public int four;
    public int six;

    public IplRunnsDao(IPLRunsCSV iplRunsCSV) {
        player = iplRunsCSV.player;
        matchs = Integer.parseInt(iplRunsCSV.matchs);
        innings = Integer.parseInt(iplRunsCSV.innings);
        runs = Integer.parseInt(iplRunsCSV.runs);
        if (iplRunsCSV.average.contains("-")) {
            average = 0;
        }
        if (!iplRunsCSV.average.contains("-")) {
            average = Double.parseDouble(iplRunsCSV.average);
        }
        strikingRates = Double.parseDouble(iplRunsCSV.strikingRates);
        four = iplRunsCSV.four;
        six = iplRunsCSV.six;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getMatchs() {
        return matchs;
    }

    public void setMatchs(int matchs) {
        this.matchs = matchs;
    }

    public int getInnings() {
        return innings;
    }

    public void setInnings(int innings) {
        this.innings = innings;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getStrikingRates() {
        return strikingRates;
    }

    public void setStrikingRates(double strikingRates) {
        this.strikingRates = strikingRates;
    }

    public int getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getSix() {
        return six;
    }

    public void setSix(int six) {
        this.six = six;
    }

    @Override
    public String toString() {
        return "IplRunnsDao{" +
                "player='" + player + '\'' +
                ", matchs=" + matchs +
                ", innings=" + innings +
                ", runs=" + runs +
                ", average=" + average +
                ", strikingRates=" + strikingRates +
                ", four=" + four +
                ", six=" + six +
                '}';
    }
}

