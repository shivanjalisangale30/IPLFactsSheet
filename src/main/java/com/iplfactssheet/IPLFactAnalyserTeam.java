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
        this.iplRunsCSVHashMap = new HashMap<>();
        this.comparatorHashMap = new HashMap<>();

        Comparator<IplRunnsDao> highAverage = Comparator.comparing(iplRunnsDao -> iplRunnsDao.average, Comparator.reverseOrder());
        Comparator<IplRunnsDao> highSix = Comparator.comparing(iplRunnsDao -> iplRunnsDao.six, Comparator.reverseOrder());
        Comparator<IplRunnsDao> highFour = Comparator.comparing(iplRunnsDao -> iplRunnsDao.four, Comparator.reverseOrder());
        Comparator<IplRunnsDao> highStrikingRates = Comparator.comparing(iplRunnsDao -> iplRunnsDao.strikingRates, Comparator.reverseOrder());
        Comparator<IplRunnsDao> highRun = Comparator.comparing(iplRunnsDao -> iplRunnsDao.runs, Comparator.reverseOrder());

        Comparator<IplRunnsDao> highSixFour = highSix.thenComparing(highFour);
        Comparator<IplRunnsDao> strikingRatesWithSixFour = highStrikingRates.thenComparing(highSixFour);
        Comparator<IplRunnsDao> highAverageWithStrikingRates = highAverage.thenComparing(highStrikingRates);
        Comparator<IplRunnsDao> highRunAverage = highRun.thenComparing(highAverage);

        this.comparatorHashMap.put(SortFieldIplRunns.AVERAGE, highAverage);
        this.comparatorHashMap.put(SortFieldIplRunns.STRIKINGRATES, highStrikingRates);
        this.comparatorHashMap.put(SortFieldIplRunns.SIXANDFOUR, highSixFour);
        this.comparatorHashMap.put(SortFieldIplRunns.SRTIKINGRATESWITHSIXFOUR, strikingRatesWithSixFour);
        this.comparatorHashMap.put(SortFieldIplRunns.AVERAGESTRIKINGRATES, highAverageWithStrikingRates);
        this.comparatorHashMap.put(SortFieldIplRunns.RUNSAVERAGE, highRunAverage);
    }

    public int loadBattingTeamData(String csvFilePath) throws IPLFactAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLRunsCSV> csvFileIterartor = csvBuilder.getCSVFileIterartor(reader, IPLRunsCSV.class);
            Iterable<IPLRunsCSV> csvIterable = () -> csvFileIterartor;
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

    public int loadBallingTeamData(String csvFilePath) throws IPLFactAnalyserException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLWktsCSV> csvFileIterartor = csvBuilder.getCSVFileIterartor(reader, IPLWktsCSV.class);
            Iterable<IPLWktsCSV> csvIterable = () -> csvFileIterartor;
            int result = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return result;
        } catch (IOException | CSVBuilderException e) {
            throw new IPLFactAnalyserException(e.getMessage(),
                    IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e) {
            throw new IPLFactAnalyserException(e.getMessage(),
                    IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE);
        }
    }
}


