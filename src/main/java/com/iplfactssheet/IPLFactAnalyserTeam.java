package com.iplfactssheet;

import com.google.gson.Gson;
import csvbuilder.CSVBuilderException;

import java.util.*;
import java.util.stream.Collectors;

public class IPLFactAnalyserTeam {

    public enum IPLTeams {BATTING, BATTING_BOWLING, BOWLING};

    HashMap<String, IplTeamDao> iplCSVHashMap = null;
    HashMap<SortFieldIplTeam, Comparator<IplTeamDao>> comparatorHashMap = null;
    IPLTeams iplTeams;

    public IPLFactAnalyserTeam(IPLTeams iplTeams) {
        this.iplCSVHashMap = new HashMap<>();
        this.comparatorHashMap = new HashMap<>();
        this.iplTeams = iplTeams;

        Comparator<IplTeamDao> highBatAverage = Comparator.comparing(iplTeamDao -> iplTeamDao.batAaverage, Comparator.reverseOrder());
        Comparator<IplTeamDao> highBallAverage = Comparator.comparing(iplTeamDao -> iplTeamDao.bowlAverage, Comparator.reverseOrder());
        Comparator<IplTeamDao> highSix = Comparator.comparing(iplTeamDao -> iplTeamDao.six, Comparator.reverseOrder());
        Comparator<IplTeamDao> highFour = Comparator.comparing(iplTeamDao -> iplTeamDao.four, Comparator.reverseOrder());
        Comparator<IplTeamDao> highStrikingRates = Comparator.comparing(iplTeamDao -> iplTeamDao.strikingRates, Comparator.reverseOrder());
        Comparator<IplTeamDao> highBatRun = Comparator.comparing(iplTeamDao -> iplTeamDao.batRuns, Comparator.reverseOrder());
        Comparator<IplTeamDao> highBowlRun = Comparator.comparing(iplTeamDao -> iplTeamDao.bowlRuns, Comparator.reverseOrder());

        Comparator<IplTeamDao> highEconomyRate = Comparator.comparing(iplTeamDao -> iplTeamDao.economyRate,Comparator.reverseOrder());
        Comparator<IplTeamDao> highFiveWkts = Comparator.comparing(iplTeamDao -> iplTeamDao.fiveWkts,Comparator.reverseOrder());
        Comparator<IplTeamDao> highFourWkts = Comparator.comparing(iplTeamDao -> iplTeamDao.fourWkts,Comparator.reverseOrder());
        Comparator<IplTeamDao> highWkts = Comparator.comparing(iplTeamDao -> iplTeamDao.wkts,Comparator.reverseOrder());

        Comparator<IplTeamDao> highSixFour = highSix.thenComparing(highFour);
        Comparator<IplTeamDao> strikingRatesWithSixFour = highStrikingRates.thenComparing(highSixFour);
        Comparator<IplTeamDao> highBatAverageWithStrikingRates = highBatAverage.thenComparing(highStrikingRates);
        Comparator<IplTeamDao> highBowlAverageWithStrikingRates = highBallAverage.thenComparing(highStrikingRates);
        Comparator<IplTeamDao> highBatRunAverage = highBatRun.thenComparing(highBatAverage);
        Comparator<IplTeamDao> highBallRunAverage = highBowlRun.thenComparing(highBallAverage);
        Comparator<IplTeamDao> highRunWkts = highBatRun.thenComparing(highWkts);

        Comparator<IplTeamDao> highFiveFourWkts = highFiveWkts.thenComparing(highFourWkts);
        Comparator<IplTeamDao> strikingRateWithFiveFourWkts = highStrikingRates.thenComparing(highFiveFourWkts);
        Comparator<IplTeamDao> highBallWktsWithAverage = highWkts.thenComparing(highBallAverage);
        Comparator<IplTeamDao> highBatBallAvearge = highBatAverage.thenComparing(highBallAverage);


        this.comparatorHashMap.put(SortFieldIplTeam.FIVE_WKTS,highFiveWkts);
        this.comparatorHashMap.put(SortFieldIplTeam.FOUR_WKTS,highFourWkts);
        this.comparatorHashMap.put(SortFieldIplTeam.BAT_AVERAGE, highBatAverage);
        this.comparatorHashMap.put(SortFieldIplTeam.BALL_AVERAGE, highBallAverage);
        this.comparatorHashMap.put(SortFieldIplTeam.STRIKING_RATES, highStrikingRates);
        this.comparatorHashMap.put(SortFieldIplTeam.SIX_AND_FOUR, highSixFour);
        this.comparatorHashMap.put(SortFieldIplTeam.SRTIKING_RATES_WITH_SIX_FOUR, strikingRatesWithSixFour);
        this.comparatorHashMap.put(SortFieldIplTeam.BAT_AVERAGE_STRIKING_RATES, highBatAverageWithStrikingRates);
        this.comparatorHashMap.put(SortFieldIplTeam.BALL_AVERAGE_STRIKING_RATES, highBowlAverageWithStrikingRates);
        this.comparatorHashMap.put(SortFieldIplTeam.BAT_RUNS_AVERAGE, highBatRunAverage);
        this.comparatorHashMap.put(SortFieldIplTeam.BALL_RUNS_AVERAGE, highBallRunAverage);
        this.comparatorHashMap.put(SortFieldIplTeam.ECONOMY_RATE,highEconomyRate);
        this.comparatorHashMap.put(SortFieldIplTeam.STRIKING_RATES_FIVE_FOUR_WKTS,strikingRateWithFiveFourWkts);
        this.comparatorHashMap.put(SortFieldIplTeam.BAll_WKTS_AVEARGE,highBallWktsWithAverage);
        this.comparatorHashMap.put(SortFieldIplTeam.BALL_BAT_AVERAGE,new BatBAllAverageComparator());
        this.comparatorHashMap.put(SortFieldIplTeam.BALL_BAT_RUN_WKTS,highRunWkts);
    }

    public int loadIplData(String... csvFilePath) throws IPLFactAnalyserException, CSVBuilderException {
        IPLAdapter iplAdapter = IPLTeamFactory.getIPLData(iplTeams);
        iplCSVHashMap = (HashMap<String, IplTeamDao>) iplAdapter.loadIplData(csvFilePath);
        return this.iplCSVHashMap.size();
    }

    public String getSortedData(SortFieldIplTeam sortBy) throws IPLFactAnalyserException {
        if (iplCSVHashMap == null || iplCSVHashMap.size() == 0) {
            throw new IPLFactAnalyserException("No census Data",
                    IPLFactAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        ArrayList censusDTO = iplCSVHashMap.values().stream()
                .sorted(this.comparatorHashMap.get(sortBy))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedJson = new Gson().toJson(censusDTO);
        return sortedJson;
    }
}
