package com.iplfactssheet;

public class IplTeamDao {

    public String player;
    public int matchs;
    public int innings;
    public int runs;
    public double average;
    public double strikingRates;
    public int four;
    public int six;
    public double over;
    public int fourWkts;
    public int fiveWkts;
    public int wkts;

    public IplTeamDao(IplRunsCsv iplRunsCSV) {
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

    public IplTeamDao(IplWktsCsv csvIplWkts) {
        player = csvIplWkts.player;
        matchs = Integer.parseInt(csvIplWkts.matchs);
        if (csvIplWkts.average.contains("-")) {
            average = 0;
        }
        if (!csvIplWkts.average.contains("-")) {
            average = Double.parseDouble(csvIplWkts.average);
        }

        if (csvIplWkts.fourWkts.contains("-")) {
            fourWkts = 0;
        }
        if (!csvIplWkts.average.contains("-")) {
            fourWkts = Integer.parseInt(csvIplWkts.fourWkts);
        }

        if (csvIplWkts.fiveWkts.contains("-")) {
            fiveWkts = 0;
        }
        if (!csvIplWkts.average.contains("-")) {
            fiveWkts = Integer.parseInt(csvIplWkts.fiveWkts);
        }

        if (csvIplWkts.strikingRates.contains("-")) {
            strikingRates = 0;
        }
        if (!csvIplWkts.average.contains("-")) {
            strikingRates = Double.parseDouble(csvIplWkts.strikingRates);
        }
    }
}
