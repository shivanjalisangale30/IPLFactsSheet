package com.iplfactssheet;

import com.opencsv.bean.CsvBindByName;

public class IplWktsCsv {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Ov", required = true)
    public double over;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Wkts", required = true)
    public int wkts;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "4w", required = true)
    public int fourWkts;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWkts;

    @CsvBindByName(column = "SR",required = true)
    public double strikingRate;

    public IplWktsCsv(String player, int matchs, int innings, double over, int runs, int wkts, double average, int fourWkts, int fiveWkts, double strikingRates) {
    }

    @Override
    public String toString() {
        return "IplWktsCsv{" +
                "player='" + player + '\'' +
                ", matches=" + matches +
                ", innings=" + innings +
                ", over=" + over +
                ", runs=" + runs +
                ", wkts=" + wkts +
                ", average=" + average +
                ", fourWkts=" + fourWkts +
                ", fiveWkts=" + fiveWkts +
                ", strikingRate=" + strikingRate +
                '}';
    }
}
