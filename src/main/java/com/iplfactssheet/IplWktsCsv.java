package com.iplfactssheet;

import com.opencsv.bean.CsvBindByName;

public class IplWktsCsv {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public String matchs;

    @CsvBindByName(column = "Inns", required = true)
    public String innings;

    @CsvBindByName(column = "Ov", required = true)
    public String over;

    @CsvBindByName(column = "Runs", required = true)
    public String bowlRuns;

    @CsvBindByName(column = "Wkts", required = true)
    public String wkts;

    @CsvBindByName(column = "Avg", required = true)
    public String bowlAverage;

    @CsvBindByName(column = "Econ", required = true)
    public String economyRate;

    @CsvBindByName(column = "SR", required = true)
    public String strikingRates;

    @CsvBindByName(column = "4w", required = true)
    public String fourWkts;

    @CsvBindByName(column = "5w", required = true)
    public String fiveWkts;

    @CsvBindByName(column = "NA")
    public String NA;

    public IplWktsCsv() {
    }


    public IplWktsCsv(String player, String matchs, String innings, String over, String bowlRuns, String wkts,
                      String bowlAverage, String economyRate, String strikingRates, String fourWkts, String fiveWkts, String NA) {
        this.player = player;
        this.matchs = matchs;
        this.innings = innings;
        this.over = over;
        this.bowlRuns = bowlRuns;
        this.wkts = wkts;
        this.bowlAverage = bowlAverage;
        this.economyRate = economyRate;
        this.strikingRates = strikingRates;
        this.fourWkts = fourWkts;
        this.fiveWkts = fiveWkts;
        this.NA = NA;
    }

    @Override
    public String toString() {
        return "IplWktsCsv{" +
                "player='" + player + '\'' +
                ", matches=" + matchs +
                ", innings=" + innings +
                ", over=" + over +
                ", bowlRuns=" + bowlRuns +
                ", wkts=" + wkts +
                ", bowlAverage=" + bowlAverage +
                ", fourWkts=" + fourWkts +
                ", fiveWkts=" + fiveWkts +
                ", strikingRate=" + strikingRates +
                '}';
    }
}
