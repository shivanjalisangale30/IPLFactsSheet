package com.iplfactssheet;

import com.google.gson.Gson;
import csvbuilder.CSVBuilderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MokitoIplFactAnalyserTest {

    String IPL_BATTING_TEAM = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostRuns.csv";
    String IPL_BOWLING_TEAM = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostWkts.csv";

    IPLFactAnalyserTeam iplRunsAdapter;
    IPLFactAnalyserTeam iplWktsAdapter;


    @Before
    public void testIplFactAnalyserUsingMokito() {
        try {
            HashMap<String, IplTeamDao> iplTeamDaoHashMap = new HashMap<>();
            IplTeamDao iplTeamDao1 = new IplTeamDao(new IplRunsCsv("David Warner", "12", "12", "692", "69.2", "143.86", 57, 21));
            IplTeamDao iplTeamDao2 = new IplTeamDao(new IplRunsCsv("KL Rahul", "14", "14", "593", "53.9", "135.38", 49, 25));
            IplTeamDao iplTeamDao3 = new IplTeamDao(new IplRunsCsv("Quinton de Kock", "16", "16", "529", "35.26", "4", 45, 25));
            IplTeamDao iplTeamDao4 = new IplTeamDao(new IplRunsCsv("Shikhar Dhawan", "16", "16", "521", "34.73", "135.67", 64, 11));
            IplTeamDao iplTeamDao5 = new IplTeamDao(new IplRunsCsv("Andre Russell", "14", "13", "510", "56.66", "204.88", 31, 52));
            iplTeamDaoHashMap.put("David Warner", iplTeamDao1);
            iplTeamDaoHashMap.put("KL Rahul", iplTeamDao2);
            iplTeamDaoHashMap.put("Quinton de Kock", iplTeamDao3);
            iplTeamDaoHashMap.put("Shikhar Dhawan", iplTeamDao4);
            iplTeamDaoHashMap.put("Andre Russell", iplTeamDao5);
            IPLRunnsAdapter iplRunnsAdapter = mock(IPLRunnsAdapter.class);
            when(iplRunnsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM)).thenReturn(iplTeamDaoHashMap);
            iplRunsAdapter = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
            iplRunsAdapter.setIplAdapter(iplRunnsAdapter);

            HashMap<String, IplTeamDao> iplTeamDaoHashMap1 = new HashMap<>();
            IplTeamDao iplTeamWktsDao1 = new IplTeamDao(new IplWktsCsv("Imran Tahir", "17", "17", "64.2", "431", "26", "16.57",
                    "6.96", "14.84", "2", "0", ""));

            IplTeamDao iplTeamWktsDao2 = new IplTeamDao(new IplWktsCsv("Kagiso Rabada", "12", "12", "47", "368", "25",
                    "0", "14.72", "7.82", "11.28", "2", ""));

            IplTeamDao iplTeamWktsDao3 = new IplTeamDao(new IplWktsCsv("Deepak Chahar", "17", "17", "64.3", "482", "22", "0", "21.9",
                    "7.47", "17.59", "0", "0"));

            IplTeamDao iplWktsTeamDao4 = new IplTeamDao(new IplWktsCsv("Shreyas Gopal", "14", "14", "48", "347", "20",
                    "0", "17.35", "7.22", "14.4", "0", "0"));
            iplTeamDaoHashMap1.put("Imran Tahir", iplTeamWktsDao1);
            iplTeamDaoHashMap1.put("Kagiso Rabada", iplTeamWktsDao2);
            iplTeamDaoHashMap1.put("Deepak Chahar", iplTeamWktsDao3);
            iplTeamDaoHashMap1.put("Shreyas Gopal", iplWktsTeamDao4);
            IPLWktsAdapter iplWktsAdapter = mock(IPLWktsAdapter.class);
            when(iplWktsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BOWLING, IPL_BOWLING_TEAM)).thenReturn(iplTeamDaoHashMap1);
            iplWktsAdapter = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
            iplWktsAdapter.setIplAdapter(iplRunnsAdapter);

        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Mock
    IPLAdapter iplAdapter;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void givenIplRunsCsvFile_ShouldReturnCorrectCount() {
        try {
            int result = iplRunsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            Assert.assertEquals(5, result);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsSampleData_WhenSortedOnAverage_ShouldReturnTopBattingAvearages() {
        try {
            iplRunsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            String sortedData = iplRunsAdapter.getSortedData(SortFieldIplTeam.BAT_AVERAGE);
            IplRunsCsv[] iplRunsCSV = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("David Warner", iplRunsCSV[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostRunnsData_WhenSortedOnStrikingRates_ShouldReturnTopStrikingRates() {
        try {
            iplRunsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            String sortedData = iplRunsAdapter.getSortedData(SortFieldIplTeam.STRIKING_RATES);
            IplRunsCsv[] iplRunsCSV = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Andre Russell", iplRunsCSV[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunnsData_WhenCompareSixAndFour_ShouldReturnPlayerHitMaximumSixAndFour() {
        try {
            iplRunsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            String sortedData = iplRunsAdapter.getSortedData(SortFieldIplTeam.SIX_AND_FOUR);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Andre Russell", iplRunsCSVS[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostRunnsData_WhenCompareStrikingRatesAndSixFour_ShouldReturnPalyerMaximumStrikingRatesWithSixFour() {
        try {
            iplRunsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            String sortedData = iplRunsAdapter.getSortedData(SortFieldIplTeam.SRTIKING_RATES_WITH_SIX_FOUR);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Andre Russell", iplRunsCSVS[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunnsData_WhenCompareAveargeAndStrikingRates_ShouldReturnPlayerMaximumAveargeWithStrikingRates() {
        try {
            iplRunsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            String sortedData = iplRunsAdapter.getSortedData(SortFieldIplTeam.BAT_AVERAGE_STRIKING_RATES);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("David Warner", iplRunsCSVS[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetMostOfRunnsData_WhenCompareRunsAndAverage_ShouldReturnPlayerMaximumRunWithAverage() {
        try {
            iplRunsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            String sortedData = iplRunsAdapter.getSortedData(SortFieldIplTeam.BAT_RUNS_AVERAGE);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("David Warner", iplRunsCSVS[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }


}







