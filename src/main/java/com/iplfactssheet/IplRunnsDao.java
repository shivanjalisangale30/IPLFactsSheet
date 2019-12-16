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

