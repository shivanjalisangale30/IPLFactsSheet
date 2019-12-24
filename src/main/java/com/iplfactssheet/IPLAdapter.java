package com.iplfactssheet;

import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdapter {

    HashMap<String, IplTeamDao> iplRunsCSVHashMap = new HashMap<>();

    public abstract Map<String, IplTeamDao> loadIplData(IPLFactAnalyserTeam.IPLTeams iplTeams,String... csvFilePath) throws IPLFactAnalyserException, CSVBuilderException;

    protected <E> HashMap<String, IplTeamDao> loadIplData(Class<E> iplCSVClass, String... csvFilePath) throws CSVBuilderException, IPLFactAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterartor = csvBuilder.getCSVFileIterartor(reader, iplCSVClass);
            Iterable<E> csvIterable = () -> csvFileIterartor;
            if (iplCSVClass.getName().equals("com.iplfactssheet.IplRunsCsv")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplRunsCsv.class::cast)
                        .forEach(csvIplRun -> this.iplRunsCSVHashMap.put(csvIplRun.player, new IplTeamDao(csvIplRun)));
            } else if (iplCSVClass.getName().equals("com.iplfactssheet.IplWktsCsv")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplWktsCsv.class::cast)
                        .forEach(csvIplWkts -> this.iplRunsCSVHashMap.put(csvIplWkts.player, new IplTeamDao(csvIplWkts)));
            }
            return iplRunsCSVHashMap;
        } catch (IOException | CSVBuilderException e) {
            throw new IPLFactAnalyserException(e.getMessage(),
                    IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLFactAnalyserException(e.getMessage(),
                    IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE);
        }
    }
}
