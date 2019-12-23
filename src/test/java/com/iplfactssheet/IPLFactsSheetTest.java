package com.iplfactssheet;


import com.google.gson.Gson;
import csvbuilder.CSVBuilderException;
import org.junit.Assert;
import org.junit.Test;

public class IPLFactsSheetTest {

    String IPL_BATTING_TEAM = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostRuns.csv";
    String EMPTY_FILE = "/home/admin1/Desktop/IPL/src/test/resources/EmptyFile.csv";
    String DELIMETER_BATTING_FILE = "/home/admin1/Desktop/IPL/src/test/resources/Delimeter_Issue_Runs.csv";
    String HEADER_BATTING_FILE = "/home/admin1/Desktop/IPL/src/test/resources/Header_Issue_Runs.csv";
    String BATTING_FILE_TYPE_NOT_SUPPORTED = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostRuns.json";
    String BATTING_FILE_PATH_INCORRECT = "/home/admin1/Desktop/IPL/src/main/resources/IPL2019FactsheetMostRuns.csv";

    String IPL_BOWLING_TEAM = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostWkts.csv";
    String DELIMETER_BALLING_FILE = "/home/admin1/Desktop/IPL/src/test/resources/Delimeter_Issue_Wkts.csv";
    String HEADER_BALLING_FILE = "/home/admin1/Desktop/IPL/src/test/resources/Header_Issue_wkts.csv";
    String BALLING_FILE_TYPE_NOT_SUPPORTED = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostWkts.json";
    String BALLING_FILE_PATH_INCORRECT = "/home/admin1/Desktop/IPL/src/main/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenProper_ShouldGiveCorrectCount() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            int result = iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
            Assert.assertEquals(100, result);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}

    }

    @Test
    public void givenNoFile_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData("");
        } catch (IPLFactAnalyserException  e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenEmptyFile_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(EMPTY_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenDelimeterIssue_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(DELIMETER_BATTING_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunsFile_WhenHeaderIssue_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(HEADER_BATTING_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenGivenFileTypeIsNotSupported_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(BATTING_FILE_TYPE_NOT_SUPPORTED);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenFilePathIsIncorrect_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(BATTING_FILE_PATH_INCORRECT);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenSortedOnAverage_ShouldReturnTopBattingAvearages() {
        try {
            IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.AVERAGE);
            IplRunsCsv[] iplRunsCSV = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("MS Dhoni", iplRunsCSV[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenSortedOnAverage_ShouldReturnLeastBattingAvearages() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.AVERAGE);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Harpreet Brar", iplRunsCSVS[iplRunsCSVS.length - 1].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostRunnsFile_WhenSortedOnStrikingRates_ShouldReturnTopStrikingRates() {
        try {
            IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.STRIKING_RATES);
            IplRunsCsv[] iplRunsCSV = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Ishant Sharma", iplRunsCSV[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostRunnsFile_WhenSortedOnStrikingRates_ShouldReturnLeastStrikingRates() {
        try {
            IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.STRIKING_RATES);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Bhuvneshwar Kumar", iplRunsCSVS[iplRunsCSVS.length - 1].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunnsFile_WhenCompareSixAndFour_ShouldReturnPlayerHitMaximumSixAndFour() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.SIX_AND_FOUR);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Andre Russell",iplRunsCSVS[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunnsFile_WhenCompareSixAndFour_ShouldReturnPlayerHitMinimumSixAndFour() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.SIX_AND_FOUR);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Shakib Al Hasan", iplRunsCSVS[iplRunsCSVS.length - 1].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }


    @Test
    public void givenIPLFactsSheetOfMostRunnsFile_WhenCompareStrikingRatesAndSixFour_ShouldReturnPalyerMaximumStrikingRatesWithSixFour() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.SRTIKING_RATES_WITH_SIX_FOUR);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Ishant Sharma",iplRunsCSVS[0].player);
        } catch (IPLFactAnalyserException |  CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunnsFile_WhenCompareStrikingRatesAndSixFour_ShouldReturnPlayerMinimumStrikingRatesWithSixFour() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.SRTIKING_RATES_WITH_SIX_FOUR);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Bhuvneshwar Kumar",iplRunsCSVS[iplRunsCSVS.length-1].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunnsFile_WhenCompareAveargeAndStrikingRates_ShouldReturnPlayerMaximumAveargeWithStrikingRates() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.AVERAGE_STRIKING_RATES);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("MS Dhoni",iplRunsCSVS[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostRunnsFile_WhenCompareAverageWithStrikinhRates_ShouldReturnPlayerMinimumAveargeWithStrikingRates() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.AVERAGE_STRIKING_RATES);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Tim Southee",iplRunsCSVS[iplRunsCSVS.length-1].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetMostOfRunnsFile_WhenCompareRunsAndAverage_ShouldReturnPlayerMaximumRunWithAverage() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.RUNS_AVERAGE);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("David Warner",iplRunsCSVS[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFatcsSheetsMostOfRunnsFile_WhenCompareRunsAndAverage_ShouldReturnPlayerMinimumRunWithAverage() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.RUNS_AVERAGE);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Tim Southee",iplRunsCSVS[iplRunsCSVS.length-1].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetMostOfWktsFile_WhenGivenProper_ShouldReturnCorrectCount() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            int result = iplFactAnalyserTeam.loadIplData(IPL_BOWLING_TEAM);
            Assert.assertEquals(99,result);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenNoFile_ProperlyShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData("");
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE,e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenEmptyFile_ProperlyShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(EMPTY_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE,e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenDelimeterIssue_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(DELIMETER_BALLING_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE,e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactSheetsOfMostWktsFile_WhenHeaderIssue_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(HEADER_BALLING_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE,e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenGivenFileTypeIsNotSupported_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(BALLING_FILE_TYPE_NOT_SUPPORTED);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenFilePathIsIncorrect_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(BALLING_FILE_PATH_INCORRECT);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenSortedOnAverage_ShouldReturnTopBowlingAvearages() {
        try {
            IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
            iplFactAnalyserTeam.loadIplData(IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.AVERAGE);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCsvs[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenSortedOnAverage_ShouldReturnLeastBowlingAvearages() {
        try {
            IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
            iplFactAnalyserTeam.loadIplData(IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.AVERAGE);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Yusuf Pathan", iplWktsCsvs[iplWktsCsvs.length-1].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }
    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenSortedOn_ShouldReturnTopStrikingRatesOfBowler() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.STRIKING_RATES);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham",iplWktsCsvs[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenSortedOn_ShouldReturnLeastStrikingRatesOfBowler() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.STRIKING_RATES);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Yusuf Pathan",iplWktsCsvs[iplWktsCsvs.length-1].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenSorted_ShouldReturnTopEconomyRateOfBowler() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.ECONOMY_RATE);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Ben Cutting",iplWktsCsvs[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenSorted_ShouldReturnLeastEconomyRateOfBowler() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.ECONOMY_RATE);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Shivam Dube",iplWktsCsvs[iplWktsCsvs.length-1].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenSorted_ShouldReturnMostStrikingRateWithFiveFourWkts() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.STRIKING_RATES_FIVE_FOUR_WKTS);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham",iplWktsCsvs[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenSorted_ShouldReturnLeastStrikingRateWithFiveFourWkts() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.STRIKING_RATES_FIVE_FOUR_WKTS);
            IplWktsCsv[] iplRunsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Yusuf Pathan",iplRunsCsvs[iplRunsCsvs.length-1].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenSorted_ShouldeReturnMostBowlingAverageWithStikingRates() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.AVERAGE_STRIKING_RATES);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham",iplWktsCsvs[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenSorted_ShouldeReturnLeastBowlingAverageWithStikingRates() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.AVERAGE_STRIKING_RATES);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Yusuf Pathan",iplWktsCsvs[iplWktsCsvs.length-1].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenSorted_ShouldeReturnMostWktsAndBowlingAverage() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.WKTS_AVEARGE);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham",iplWktsCsvs[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenSorted_ShouldeReturnLeastWktsAndBowlingAverage() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.WKTS_AVEARGE);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Yusuf Pathan",iplWktsCsvs[iplWktsCsvs.length-1].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunnsAndWktsFiles_WhenSorted_ShouldReturnMostBallingAndBattingAvearge() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING_BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM, IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.BALL_BAT_AVERAGE);
            IplTeamDao[] iplTeamDaos = new Gson().fromJson(sortedData, IplTeamDao[].class);
            Assert.assertEquals("MS Dhoni",iplTeamDaos[iplTeamDaos.length-1].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.getMessage());
        }
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunnsAndWktsFiles_WhenSorted_ShouldReturnLeastBallingAndBattingAvearge() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING_BOWLING);
        try {
            iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM, IPL_BOWLING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.BALL_BAT_AVERAGE);
            IplTeamDao[] iplTeamDaos = new Gson().fromJson(sortedData, IplTeamDao[].class);
            Assert.assertEquals("Krishnappa Gowtham",iplTeamDaos[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.getMessage());
        }
    }
}
