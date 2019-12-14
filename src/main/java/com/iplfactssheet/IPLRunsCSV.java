package com.iplfactssheet;

import com.opencsv.bean.CsvBindByName;

public class IPLRunsCSV {
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

    public IPLRunsCSV() {
    }

    public IPLRunsCSV(String player, String matchs, String innings, String runs, String average) {
        this.player = player;
        this.matchs = matchs;
        this.innings = innings;
        this.runs = runs;
        this.average = average;
    }

    public IPLRunsCSV(String player, double average, int runs, int matchs, int innings) {
    }

    @Override
    public String toString() {
        return "IPLRunsCSV{" +
                "player='" + player + '\'' +
                ", matchs=" + matchs +
                ", innings=" + innings +
                ", runs=" + runs +
                ", average=" + average +
                '}';
    }
}
