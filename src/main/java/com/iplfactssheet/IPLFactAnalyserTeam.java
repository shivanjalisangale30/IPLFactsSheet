package com.iplfactssheet;

import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class IPLFactAnalyserTeam {

    public int loadBattingTeamData(String csvFilePath) throws IPLFactAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLRunsCSV> stateCSVIterator = csvBuilder.getCSVFileIterartor(reader, IPLRunsCSV.class);
            Iterable<IPLRunsCSV> csvIterable = () -> stateCSVIterator;
            int counter = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return counter;
        } catch (IOException | CSVBuilderException e) {
            throw new IPLFactAnalyserException(e.getMessage(),
                    IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e){
            throw new IPLFactAnalyserException(e.getMessage(),
                    IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE);
        }
    }
}
