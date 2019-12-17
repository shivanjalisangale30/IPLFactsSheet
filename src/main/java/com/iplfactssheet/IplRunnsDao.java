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

    public IplRunnsDao(IplRunsCsv iplRunsCSV) {
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

    public IplRunnsDao(IplWktsCsv csvIplWkts) {
        player = csvIplWkts.player;
        matchs = csvIplWkts.matches;
        average = csvIplWkts.average;
        innings = csvIplWkts.innings;
        runs = csvIplWkts.runs;
        overs = csvIplWkts.over;
        fourWkts = csvIplWkts.fourWkts;
        fiveWkts = csvIplWkts.fiveWkts;
        wkts = csvIplWkts.wkts;
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

