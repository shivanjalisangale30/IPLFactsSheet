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
    public String runs;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

    @CsvBindByName(column = "SR", required = true)
    public String strikingRates;

    @CsvBindByName(column = "4s", required = true)
    public int four;

    @CsvBindByName(column = "6s", required = true)
    public int six;

    public IplRunsCsv() {
    }

    public IplRunsCsv(String player, String matchs, String innings, String runs, String average, String strikingRates, int four, int six) {
        this.player = player;
        this.matchs = matchs;
        this.innings = innings;
        this.runs = runs;
        this.average = average;
        this.strikingRates = strikingRates;
        this.four = four;
        this.six = six;
    }

    @Override
    public String toString() {
        return "IPLRunsCSV{" +
                "player='" + player + '\'' +
                ", matchs='" + matchs + '\'' +
                ", innings='" + innings + '\'' +
                ", runs='" + runs + '\'' +
                ", average='" + average + '\'' +
                ", strikingRates='" + strikingRates + '\'' +
                ", four=" + four +
                ", six=" + six +
                '}';
    }
}
