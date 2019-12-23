package com.iplfactssheet;

import com.opencsv.bean.CsvBindByName;

public class IplRunsCsv {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public String matchs;

    @CsvBindByName(column = "Inns", required = true)
    public String innings;

    @CsvBindByName(column = "Runs", required = true)
    public String batRuns;

    @CsvBindByName(column = "Avg", required = true)
    public String batAaverage;

    @CsvBindByName(column = "SR", required = true)
    public String strikingRates;

    @CsvBindByName(column = "4s", required = true)
    public int four;

    @CsvBindByName(column = "6s", required = true)
    public int six;

    public IplRunsCsv() {
    }

    public IplRunsCsv(String player, String matchs, String innings, String batRuns, String batAaverage, String strikingRates, int four, int six) {
        this.player = player;
        this.matchs = matchs;
        this.innings = innings;
        this.batRuns = batRuns;
        this.batAaverage = batAaverage;
        this.strikingRates = strikingRates;
        this.four = four;
        this.six = six;
    }

    public IplRunsCsv(String player, int matchs, int innings, int batRuns, double batAaverage, double strikingRates, int four, int six) {
    }

    @Override
    public String toString() {
        return "IPLRunsCSV{" +
                "player='" + player + '\'' +
                ", matchs='" + matchs + '\'' +
                ", innings='" + innings + '\'' +
                ", batRuns='" + batRuns + '\'' +
                ", batAaverage='" + batAaverage + '\'' +
                ", strikingRates='" + strikingRates + '\'' +
                ", four=" + four +
                ", six=" + six +
                '}';
    }
}
