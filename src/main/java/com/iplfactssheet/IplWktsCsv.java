package com.iplfactssheet;

import com.opencsv.bean.CsvBindByName;

public class IplWktsCsv {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public String  matchs;

    @CsvBindByName(column = "Inns", required = true)
    public String innings;

    @CsvBindByName(column = "Ov", required = true)
    public String  over;

    @CsvBindByName(column = "Runs", required = true)
    public String  runs;

    @CsvBindByName(column = "Wkts", required = true)
    public String  wkts;

    @CsvBindByName(column = "Avg", required = true)
    public String  average;

    @CsvBindByName(column = "4w", required = true)
    public String  fourWkts;

    @CsvBindByName(column = "5w", required = true)
    public String  fiveWkts;

    @CsvBindByName(column = "SR",required = true)
    public String  strikingRates;

    @CsvBindByName(column = "Econ",required = true)
    public String economyRate;

    @CsvBindByName(column = "NA")
    public String NA;

    public IplWktsCsv() {
    }

    public IplWktsCsv(String player, String matchs, String innings, String over, String runs, String wkts, String average,
                      String fourWkts, String fiveWkts, String strikingRates, String economyRate, String NA) {
        this.player = player;
        this.matchs = matchs;
        this.innings = innings;
        this.over = over;
        this.runs = runs;
        this.wkts = wkts;
        this.average = average;
        this.fourWkts = fourWkts;
        this.fiveWkts = fiveWkts;
        this.strikingRates = strikingRates;
        this.economyRate = economyRate;
        this.NA = NA;
    }

    public IplWktsCsv(String player, int matchs, int innings, double over, int runs, int wkts,
                      double average, int fourWkts, int fiveWkts, double strikingRates ,double economyRate) {
    }

    @Override
    public String toString() {
        return "IplWktsCsv{" +
                "player='" + player + '\'' +
                ", matches=" + matchs +
                ", innings=" + innings +
                ", over=" + over +
                ", runs=" + runs +
                ", wkts=" + wkts +
                ", average=" + average +
                ", fourWkts=" + fourWkts +
                ", fiveWkts=" + fiveWkts +
                ", strikingRate=" + strikingRates +
                '}';
    }
}
