package com.iplfactssheet;

import org.junit.Assert;
import org.junit.Test;

public class IPLFactsSheetTest {

    String IPL_BATTING_TEAM = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostRuns.csv";
    String EMPTY_FILE = "/home/admin1/Desktop/IPL/src/test/resources/EmptyFile.csv";
    String DELIMETER_BATTING_FILE = "/home/admin1/Desktop/IPL/src/test/resources/Delimeter_Issue_Runs.csv";
    String FILE_TYPE_NOT_SUPPORTED = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostRuns.json";


    @Test
    public void givenIPLFactsSheetOfMostRunsFile_WhenProper_ShouldGiveCorrectCount() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
        try {
            int result = iplFactAnalyserTeam.loadBattingTeamData(IPL_BATTING_TEAM);
            Assert.assertEquals(101, result);
        } catch (IPLFactAnalyserException e) {
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
    public void givenIPLFactsSheetOfMostRunsFile_WhenGivenFileTypeIsNotSupported_ShouldHandleException() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
        try {
            iplFactAnalyserTeam.loadBattingTeamData(FILE_TYPE_NOT_SUPPORTED);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
}
