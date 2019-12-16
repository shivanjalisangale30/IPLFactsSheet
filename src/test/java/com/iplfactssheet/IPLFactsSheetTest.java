package com.iplfactssheet;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class IPLFactsSheetTest {

    String IPL_BATTING_TEAM = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostRuns.csv";
    String EMPTY_FILE = "/home/admin1/Desktop/IPL/src/test/resources/EmptyFile.csv";
    String DELIMETER_BATTING_FILE = "/home/admin1/Desktop/IPL/src/test/resources/Delimeter_Issue_Runs.csv";
    String HEADER_BATTING_FILE = "/home/admin1/Desktop/IPL/src/test/resources/Header_Issue_Runs.csv";
    String FILE_TYPE_NOT_SUPPORTED = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostRuns.json";
    String FILE_PATH_INCORRECT = "/home/admin1/Desktop/IPL/src/main/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenProper_ShouldGiveCorrectCount() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
        try {
            int result = iplFactAnalyserTeam.loadBattingTeamData(IPL_BATTING_TEAM);
            Assert.assertEquals(100, result);
        } catch (IPLFactAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenNoFile_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
        try {
            iplFactAnalyserTeam.loadBattingTeamData("");
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        }
    }

    @Test
    public void givenEmptyFile_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
        try {
            iplFactAnalyserTeam.loadBattingTeamData(EMPTY_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenDelimeterIssue_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
        try {
            iplFactAnalyserTeam.loadBattingTeamData(DELIMETER_BATTING_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunsFile_WhenHeaderIssue_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
        try {
            iplFactAnalyserTeam.loadBattingTeamData(HEADER_BATTING_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenGivenFileTypeIsNotSupported_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
        try {
            iplFactAnalyserTeam.loadBattingTeamData(FILE_TYPE_NOT_SUPPORTED);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenFilePathIsIncorrect_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
        try {
            iplFactAnalyserTeam.loadBattingTeamData(FILE_PATH_INCORRECT);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenSortedOnAverage_ShouldReturnTopBattingAvearages() {
        try {
            IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
            iplFactAnalyserTeam.loadBattingTeamData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplRunns.AVERAGE);
            IPLRunsCSV[] iplRunsCSV = new Gson().fromJson(sortedData, IPLRunsCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRunsCSV[0].player);
        } catch (IPLFactAnalyserException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenSortedOnAverage_ShouldReturnLeastBattingAvearages() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
        try {
            iplFactAnalyserTeam.loadBattingTeamData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplRunns.AVERAGE);
            IPLRunsCSV[] iplRunsCSVS = new Gson().fromJson(sortedData, IPLRunsCSV[].class);
            Assert.assertEquals("Harpreet Brar", iplRunsCSVS[iplRunsCSVS.length - 1].player);
        } catch (IPLFactAnalyserException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostRunnsFile_WhenSortedOnStrikingRates_ShouldReturnTopStrikingRates() {
        try {
            IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
            iplFactAnalyserTeam.loadBattingTeamData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplRunns.STRIKINGRATES);
            IPLRunsCSV[] iplRunsCSV = new Gson().fromJson(sortedData, IPLRunsCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunsCSV[0].player);
        } catch (IPLFactAnalyserException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostRunnsFile_WhenSortedOnStrikingRates_ShouldReturnLeastStrikingRates() {
        try {
            IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
            iplFactAnalyserTeam.loadBattingTeamData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplRunns.STRIKINGRATES);
            IPLRunsCSV[] iplRunsCSVS = new Gson().fromJson(sortedData, IPLRunsCSV[].class);
            Assert.assertEquals("Bhuvneshwar Kumar", iplRunsCSVS[iplRunsCSVS.length - 1].player);
        } catch (IPLFactAnalyserException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunnsFile_WhenCompareSixAndFour_ShouldReturnMaximumSixAndFourPlayer() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
        try {
            iplFactAnalyserTeam.loadBattingTeamData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplRunns.SIXANDFOUR);
            IPLRunsCSV[] iplRunsCSVS = new Gson().fromJson(sortedData, IPLRunsCSV[].class);
            Assert.assertEquals("Andre Russell",iplRunsCSVS[0].player);
        } catch (IPLFactAnalyserException e) {
            e.printStackTrace();
        }
    }
}
