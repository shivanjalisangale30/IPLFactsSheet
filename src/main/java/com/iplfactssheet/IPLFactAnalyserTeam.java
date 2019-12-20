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

        Comparator<IplTeamDao> highAverage = Comparator.comparing(iplRunnsDao -> iplRunnsDao.average, Comparator.reverseOrder());
        Comparator<IplTeamDao> highSix = Comparator.comparing(iplRunnsDao -> iplRunnsDao.six, Comparator.reverseOrder());
        Comparator<IplTeamDao> highFour = Comparator.comparing(iplRunnsDao -> iplRunnsDao.four, Comparator.reverseOrder());
        Comparator<IplTeamDao> highStrikingRates = Comparator.comparing(iplRunnsDao -> iplRunnsDao.strikingRates, Comparator.reverseOrder());
        Comparator<IplTeamDao> highRun = Comparator.comparing(iplRunnsDao -> iplRunnsDao.runs, Comparator.reverseOrder());

        Comparator<IplTeamDao> highSixFour = highSix.thenComparing(highFour);
        Comparator<IplTeamDao> strikingRatesWithSixFour = highStrikingRates.thenComparing(highSixFour);
        Comparator<IplTeamDao> highAverageWithStrikingRates = highAverage.thenComparing(highStrikingRates);
        Comparator<IplTeamDao> highRunAverage = highRun.thenComparing(highAverage);

        this.comparatorHashMap.put(SortFieldIplRunns.AVERAGE, highAverage);
        this.comparatorHashMap.put(SortFieldIplRunns.STRIKING_RATES, highStrikingRates);
        this.comparatorHashMap.put(SortFieldIplRunns.SIX_AND_FOUR, highSixFour);
        this.comparatorHashMap.put(SortFieldIplRunns.SRTIKING_RATES_WITH_SIX_FOUR, strikingRatesWithSixFour);
        this.comparatorHashMap.put(SortFieldIplRunns.AVERAGE_STRIKING_RATES, highAverageWithStrikingRates);
        this.comparatorHashMap.put(SortFieldIplRunns.RUNS_AVERAGE, highRunAverage);
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
