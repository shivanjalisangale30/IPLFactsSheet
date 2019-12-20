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
        matchs = csvIplWkts.matches;
        average = csvIplWkts.average;
        innings = csvIplWkts.innings;
        runs = csvIplWkts.runs;
        over = csvIplWkts.over;
        fourWkts = csvIplWkts.fourWkts;
        fiveWkts = csvIplWkts.fiveWkts;
        wkts = csvIplWkts.wkts;
    }


    public Object getDTO(IPLFactAnalyserTeam.IPLTeams iplTeams) {
        if(iplTeams.equals(IPLFactAnalyserTeam.IPLTeams.BATTING))
            return new IplRunsCsv(player,  matchs,  innings, runs, average, strikingRates, four,six);
        return new IplWktsCsv(player,matchs,innings,over,runs,wkts,average,fourWkts,fiveWkts,strikingRates);
    }
}
