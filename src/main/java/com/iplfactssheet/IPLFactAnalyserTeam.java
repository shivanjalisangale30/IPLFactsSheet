package com.iplfactssheet;

import com.google.gson.Gson;
import csvbuilder.CSVBuilderException;
import java.util.*;
import java.util.stream.Collectors;

public class IPLFactAnalyserTeam {

    public enum IPLTeams {BATTING, BOWLING};

    HashMap<String, IplTeamDao> iplCSVHashMap = null;
    HashMap<SortFieldIplRunns, Comparator<IplTeamDao>> comparatorHashMap = null;
    IPLTeams iplTeams;

    public IPLFactAnalyserTeam(IPLTeams iplTeams) {
        this.iplCSVHashMap = new HashMap<>();
        this.comparatorHashMap = new HashMap<>();
        this.iplTeams = iplTeams;

        Comparator<IplTeamDao> highAverage = Comparator.comparing(iplTeamDao -> iplTeamDao.average, Comparator.reverseOrder());
        Comparator<IplTeamDao> highSix = Comparator.comparing(iplTeamDao -> iplTeamDao.six, Comparator.reverseOrder());
        Comparator<IplTeamDao> highFour = Comparator.comparing(iplTeamDao -> iplTeamDao.four, Comparator.reverseOrder());
        Comparator<IplTeamDao> highStrikingRates = Comparator.comparing(iplTeamDao -> iplTeamDao.strikingRates, Comparator.reverseOrder());
        Comparator<IplTeamDao> highRun = Comparator.comparing(iplTeamDao -> iplTeamDao.runs, Comparator.reverseOrder());
        Comparator<IplTeamDao> highEconomyRate = Comparator.comparing(iplTeamDao -> iplTeamDao.economyRate,Comparator.reverseOrder());
        Comparator<IplTeamDao> highFiveWkts = Comparator.comparing(iplTeamDao -> iplTeamDao.fiveWkts,Comparator.reverseOrder());
        Comparator<IplTeamDao> highFourWkts = Comparator.comparing(iplTeamDao -> iplTeamDao.fourWkts,Comparator.reverseOrder());

        Comparator<IplTeamDao> highSixFour = highSix.thenComparing(highFour);
        Comparator<IplTeamDao> strikingRatesWithSixFour = highStrikingRates.thenComparing(highSixFour);
        Comparator<IplTeamDao> highAverageWithStrikingRates = highAverage.thenComparing(highStrikingRates);
        Comparator<IplTeamDao> highRunAverage = highRun.thenComparing(highAverage);
        Comparator<IplTeamDao> highFiveFourWkts = highFiveWkts.thenComparing(highFourWkts);
        Comparator<IplTeamDao> strikingRateWithFiveFourWkts = highStrikingRates.thenComparing(highFiveFourWkts);

        this.comparatorHashMap.put(SortFieldIplRunns.AVERAGE, highAverage);
        this.comparatorHashMap.put(SortFieldIplRunns.STRIKING_RATES, highStrikingRates);
        this.comparatorHashMap.put(SortFieldIplRunns.SIX_AND_FOUR, highSixFour);
        this.comparatorHashMap.put(SortFieldIplRunns.SRTIKING_RATES_WITH_SIX_FOUR, strikingRatesWithSixFour);
        this.comparatorHashMap.put(SortFieldIplRunns.AVERAGE_STRIKING_RATES, highAverageWithStrikingRates);
        this.comparatorHashMap.put(SortFieldIplRunns.RUNS_AVERAGE, highRunAverage);
        this.comparatorHashMap.put(SortFieldIplRunns.ECONOMY_RATE,highEconomyRate);
        this.comparatorHashMap.put(SortFieldIplRunns.STRIKING_RATES_FIVE_FOUR_WKTS,strikingRateWithFiveFourWkts);
    }

    public int loadIplData(String csvFilePath) throws IPLFactAnalyserException, CSVBuilderException {
        IPLAdapter iplAdapter = IPLTeamFactory.getIPLData(iplTeams);
        iplCSVHashMap = (HashMap<String, IplTeamDao>) iplAdapter.loadIplData(csvFilePath);
        return iplCSVHashMap.size();
    }

    public String getSortedData(SortFieldIplRunns sortBy) throws IPLFactAnalyserException {
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
