package com.iplfactssheet;

import csvbuilder.CSVBuilderException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IPLRunnsAdapterTest {

    String IPL_BATTING_TEAM = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostRuns.csv";
    String EMPTY_FILE = "/home/admin1/Desktop/IPL/src/test/resources/EmptyFile.csv";
    String DELIMETER_BATTING_FILE = "/home/admin1/Desktop/IPL/src/test/resources/Delimeter_Issue_Runs.csv";
    String HEADER_BATTING_FILE = "/home/admin1/Desktop/IPL/src/test/resources/Header_Issue_Runs.csv";
    String BATTING_FILE_TYPE_NOT_SUPPORTED = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostRuns.json";
    String BATTING_FILE_PATH_INCORRECT = "/home/admin1/Desktop/IPL/src/main/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenIPLRunCsvFile_WhenProper_ShouldGiveCorrectCount() {
        try {
            IPLRunnsAdapter iplRunnsAdapter = new IPLRunnsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap = iplRunnsAdapter.loadIplData(IPL_BATTING_TEAM);
            Assert.assertEquals(100,stringIplTeamDaoMap.size() );
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenNoFile_ShouldHandleException() {
        try {
            IPLRunnsAdapter iplRunnsAdapter = new IPLRunnsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap = iplRunnsAdapter.loadIplData("");
        } catch (IPLFactAnalyserException  e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenEmptyFile_ShouldHandleException() {
        try {
            IPLRunnsAdapter iplRunnsAdapter = new IPLRunnsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap = iplRunnsAdapter.loadIplData(EMPTY_FILE);
        }
        catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLRunCsvFile_WhenDelimeterIssue_ShouldHandleException() {
        try {
            IPLRunnsAdapter iplRunnsAdapter = new IPLRunnsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap = iplRunnsAdapter.loadIplData(DELIMETER_BATTING_FILE);
        }
        catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunsFile_WhenHeaderIssue_ShouldHandleException() {
        try {
            IPLRunnsAdapter iplRunnsAdapter = new IPLRunnsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap = iplRunnsAdapter.loadIplData(HEADER_BATTING_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenGivenFileTypeIsNotSupported_ShouldHandleException() {
        try {
            IPLRunnsAdapter iplRunnsAdapter = new IPLRunnsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap = iplRunnsAdapter.loadIplData(BATTING_FILE_TYPE_NOT_SUPPORTED);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenFilePathIsIncorrect_ShouldHandleException() {
        try {
            IPLRunnsAdapter iplRunnsAdapter = new IPLRunnsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap = iplRunnsAdapter.loadIplData(BATTING_FILE_PATH_INCORRECT);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (CSVBuilderException e) {}
    }
}
