package com.iplfactssheet;

import csvbuilder.CSVBuilderException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IplRunWktsAdapterTest {

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
    public void givenIPLRunAndWktsCsvFile_WhenProper_ShouldGiveCorrectCount() {
        try {
            IPLRunnsWktsAdapter iplRunnsWktsAdapter = new IPLRunnsWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplRunnsWktsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING,
                                                           IPL_BATTING_TEAM, IPL_BOWLING_TEAM);
            Assert.assertEquals(150, stringIplTeamDaoMap1.size());
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenOneFileAndNoFile_ShouldHandleException() {
        try {
            IPLRunnsWktsAdapter iplRunnsWktsAdapter = new IPLRunnsWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplRunnsWktsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING,IPL_BATTING_TEAM, "");
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {
        }
    }

    @Test
    public void givenOneFileAndEmptyFile_ShouldHandleException() {
        try {
            IPLRunnsWktsAdapter iplRunnsWktsAdapter = new IPLRunnsWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplRunnsWktsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING,EMPTY_FILE, IPL_BOWLING_TEAM);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLRunAndWktsCsvFile_WhenDelimeterIssue_ShouldHandleException() {
        try {
            IPLRunnsWktsAdapter iplRunnsWktsAdapter = new IPLRunnsWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplRunnsWktsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING,
                                                                                        IPL_BATTING_TEAM, DELIMETER_BALLING_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunsAndWktsFile_WhenHeaderIssue_ShouldHandleException() {
        try {
            IPLRunnsWktsAdapter iplRunnsWktsAdapter = new IPLRunnsWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplRunnsWktsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING,
                                                           HEADER_BATTING_FILE, IPL_BOWLING_TEAM);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsAndWktsFile_WhenGivenFileTypeIsNotSupported_ShouldHandleException() {
        try {
            IPLRunnsWktsAdapter iplRunnsWktsAdapter = new IPLRunnsWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplRunnsWktsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING,
                                                           IPL_BATTING_TEAM, BALLING_FILE_TYPE_NOT_SUPPORTED);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsAndWktsFile_WhenFilePathIsIncorrect_ShouldHandleException() {
        try {
            IPLRunnsWktsAdapter iplRunnsWktsAdapter = new IPLRunnsWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplRunnsWktsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING,
                                                           BALLING_FILE_PATH_INCORRECT, IPL_BOWLING_TEAM);
        }  catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (CSVBuilderException e) {
        }
    }
}
