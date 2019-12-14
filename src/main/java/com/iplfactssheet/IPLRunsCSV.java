package com.iplfactssheet;

import com.opencsv.bean.CsvBindByName;

public class IPLRunsCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int matchs;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

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
