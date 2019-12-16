package com.iplfactssheet;

import com.google.gson.Gson;
import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IPLFactAnalyserTeam {

    HashMap<String, IplRunnsDao> iplRunsCSVHashMap = null;
    HashMap<SortFieldIplRunns, Comparator<IplRunnsDao>> comparatorHashMap = null;

    public IPLFactAnalyserTeam() {
        this.iplRunsCSVHashMap = new HashMap<String, IplRunnsDao>();
        this.comparatorHashMap = new HashMap<>();
        this.comparatorHashMap.put(SortFieldIplRunns.AVERAGE, Comparator.comparing(iplRunnsDao -> iplRunnsDao.average, Comparator.reverseOrder()));
        this.comparatorHashMap.put(SortFieldIplRunns.STRIKINGRATES, Comparator.comparing(iplRunnsDao -> iplRunnsDao.strikingRates, Comparator.reverseOrder()));

        Comparator<IplRunnsDao> numberOfSix = Comparator.comparing(comparatorHashMap -> comparatorHashMap.six,Comparator.reverseOrder());
        Comparator<IplRunnsDao> numberOfFour = Comparator.comparing(comparatorHashMap -> comparatorHashMap.four,Comparator.reverseOrder());
        Comparator<IplRunnsDao> result = numberOfSix.thenComparing(numberOfFour);
        this.comparatorHashMap.put(SortFieldIplRunns.SIXANDFOUR, result);
    }

    public int loadBattingTeamData(String csvFilePath) throws IPLFactAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLRunsCSV> stateCSVIterator = csvBuilder.getCSVFileIterartor(reader, IPLRunsCSV.class);
            Iterable<IPLRunsCSV> csvIterable = () -> stateCSVIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IPLRunsCSV.class::cast)
                    .forEach(csvIplRun -> this.iplRunsCSVHashMap.put(csvIplRun.player, new IplRunnsDao(csvIplRun)));
            return this.iplRunsCSVHashMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new IPLFactAnalyserException(e.getMessage(),
                    IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLFactAnalyserException(e.getMessage(),
                    IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE);
        }
    }

    public String getSortedData(SortFieldIplRunns sortBy) throws IPLFactAnalyserException {
        if (iplRunsCSVHashMap == null || iplRunsCSVHashMap.size() == 0) {
            throw new IPLFactAnalyserException("No census Data",
                    IPLFactAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        ArrayList censusDTO = iplRunsCSVHashMap.values().stream()
                .sorted(this.comparatorHashMap.get(sortBy))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedJson = new Gson().toJson(censusDTO);
        return sortedJson;
    }
}


