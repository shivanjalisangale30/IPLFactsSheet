package com.iplfactssheet;

public class IplTeamDao {

    public String player;
    public int matchs;
    public int innings;
    public double strikingRates;
    public int four;
    public int six;
    public double over;
    public int fourWkts;
    public int fiveWkts;
    public int wkts;
    public double economyRate;
    public double batAaverage;
    public double bowlAverage;
    public int bowlRuns;
    public int batRuns;

    public IplTeamDao(IplRunsCsv iplRunsCSV) {
        player = iplRunsCSV.player;
        matchs = Integer.parseInt(iplRunsCSV.matchs);
        innings = Integer.parseInt(iplRunsCSV.innings);
        batRuns = Integer.parseInt(iplRunsCSV.batRuns);
        if (iplRunsCSV.batAaverage.contains("-")) {
            batAaverage = 0;
        }
        if (!iplRunsCSV.batAaverage.contains("-")) {
            batAaverage = Double.parseDouble(iplRunsCSV.batAaverage);
        }
        strikingRates = Double.parseDouble(iplRunsCSV.strikingRates);
        four = iplRunsCSV.four;
        six = iplRunsCSV.six;

    }

    public IplTeamDao(IplWktsCsv csvIplWkts) {
        player = csvIplWkts.player;
        matchs = Integer.parseInt(csvIplWkts.matchs);

        if (csvIplWkts.bowlAverage.contains("-")) {
            bowlAverage = 0;
        }
        if (!csvIplWkts.bowlAverage.contains("-")) {
            bowlAverage = Double.parseDouble(csvIplWkts.bowlAverage);
        }

        if (csvIplWkts.fourWkts.contains("-")) {
            fourWkts = 0;
        }
        if (!csvIplWkts.fourWkts.contains("-")) {
            fourWkts = Integer.parseInt(csvIplWkts.fourWkts);
        }

        if (csvIplWkts.fiveWkts.contains("-")) {
            fiveWkts = 0;
        }
        if (!csvIplWkts.fiveWkts.contains("-")) {
            fiveWkts = Integer.parseInt(csvIplWkts.fiveWkts);
        }

        if (csvIplWkts.strikingRates.contains("-")) {
            strikingRates = 0;
        }
        if (!csvIplWkts.strikingRates.contains("-")) {
            strikingRates = Double.parseDouble(csvIplWkts.strikingRates);
        }

        if (csvIplWkts.economyRate.contains("-")) {
            economyRate = 0;
        }
        if (!csvIplWkts.economyRate.contains("-")) {
            economyRate = Double.parseDouble(csvIplWkts.economyRate);
        }
    }
}
