package com.iplfactssheet;

import csvbuilder.CSVBuilderException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IplWktsAdapterTest {

    String IPL_BOWLING_TEAM = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostWkts.csv";
    String DELIMETER_BALLING_FILE = "/home/admin1/Desktop/IPL/src/test/resources/Delimeter_Issue_Wkts.csv";
    String HEADER_BALLING_FILE = "/home/admin1/Desktop/IPL/src/test/resources/Header_Issue_wkts.csv";
    String BALLING_FILE_TYPE_NOT_SUPPORTED = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostWkts.json";
    String BALLING_FILE_PATH_INCORRECT = "/home/admin1/Desktop/IPL/src/main/resources/IPL2019FactsheetMostWkts.csv";
    String EMPTY_FILE = "/home/admin1/Desktop/IPL/src/test/resources/EmptyFile.csv";


    @Test
    public void givenIPLWktsCsvFile_WhenProper_ShouldGiveCorrectCount() {
        try {
            IPLWktsAdapter iplWktsAdapter = new IPLWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplWktsAdapter.loadIplData(IPL_BOWLING_TEAM);
            Assert.assertEquals(99,stringIplTeamDaoMap1.size() );
        } catch (IPLFactAnalyserException | CSVBuilderException e) {}
    }

    @Test
    public void givenNoFile_ShouldHandleException() {
        try {
            IPLWktsAdapter iplWktsAdapter = new IPLWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplWktsAdapter.loadIplData("");
        } catch (IPLFactAnalyserException  e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenEmptyFile_ShouldHandleException() {
        try {
            IPLWktsAdapter iplWktsAdapter = new IPLWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplWktsAdapter.loadIplData(EMPTY_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLWktsCsvFile_WhenDelimeterIssue_ShouldHandleException() {
        try {
            IPLWktsAdapter iplWktsAdapter = new IPLWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplWktsAdapter.loadIplData(DELIMETER_BALLING_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetsOfMostWktsFile_WhenHeaderIssue_ShouldHandleException() {
        try {
            IPLWktsAdapter iplWktsAdapter = new IPLWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplWktsAdapter.loadIplData(HEADER_BALLING_FILE);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenGivenFileTypeIsNotSupported_ShouldHandleException() {
        try {
            IPLWktsAdapter iplWktsAdapter = new IPLWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplWktsAdapter.loadIplData(BALLING_FILE_TYPE_NOT_SUPPORTED);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (CSVBuilderException e) {}
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsFile_WhenFilePathIsIncorrect_ShouldHandleException() {
        try {
            IPLWktsAdapter iplWktsAdapter = new IPLWktsAdapter();
            Map<String, IplTeamDao> stringIplTeamDaoMap1 = iplWktsAdapter.loadIplData(BALLING_FILE_PATH_INCORRECT);
        } catch (IPLFactAnalyserException e) {
            Assert.assertEquals(IPLFactAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (CSVBuilderException e) {}
    }
}
