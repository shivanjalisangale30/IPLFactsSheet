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

    IPLFactAnalyserTeam iplObject1;
    IPLFactAnalyserTeam iplObject2;


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
            iplObject1 = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BATTING);
            iplObject1.setIplAdapter(iplRunnsAdapter);

            HashMap<String, IplTeamDao> iplTeamDaoHashMap1 = new HashMap<>();
            IplTeamDao iplTeamWktsDao1 = new IplTeamDao(new IplWktsCsv("Imran Tahir", "17", "17", "64.2", "431", "26", "16.57",
                    "6.96", "14.84", "2", "0", ""));

            IplTeamDao iplTeamWktsDao2 = new IplTeamDao(new IplWktsCsv("Kagiso Rabada", "12", "12", "47", "368", "25",
                    "0", "14.72", "7.82", "11", "2", ""));

            IplTeamDao iplTeamWktsDao3 = new IplTeamDao(new IplWktsCsv("Deepak Chahar", "17", "17", "64.3", "482", "22", "0", "21.9",
                    "7.47", "17", "0", "0"));

            IplTeamDao iplWktsTeamDao4 = new IplTeamDao(new IplWktsCsv("Shreyas Gopal", "14", "14", "48", "347", "20",
                    "0", "17.35", "7.22", "14", "0", "0"));
            iplTeamDaoHashMap1.put("Imran Tahir", iplTeamWktsDao1);
            iplTeamDaoHashMap1.put("Kagiso Rabada", iplTeamWktsDao2);
            iplTeamDaoHashMap1.put("Deepak Chahar", iplTeamWktsDao3);
            iplTeamDaoHashMap1.put("Shreyas Gopal", iplWktsTeamDao4);
            IPLWktsAdapter iplWktsAdapter = mock(IPLWktsAdapter.class);
            when(iplWktsAdapter.loadIplData(IPLFactAnalyserTeam.IPLTeams.BOWLING, IPL_BOWLING_TEAM)).thenReturn(iplTeamDaoHashMap1);
            iplObject2 = new IPLFactAnalyserTeam(IPLFactAnalyserTeam.IPLTeams.BOWLING);
            iplObject2.setIplAdapter(iplWktsAdapter);
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
            int result = iplObject1.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            Assert.assertEquals(5, result);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsSampleData_WhenSortedOnAverage_ShouldReturnTopBattingAvearages() {
        try {
            iplObject1.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            String sortedData = iplObject1.getSortedData(SortFieldIplTeam.BAT_AVERAGE);
            IplRunsCsv[] iplRunsCSV = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("David Warner", iplRunsCSV[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostRunnsData_WhenSortedOnStrikingRates_ShouldReturnTopStrikingRates() {
        try {
            iplObject1.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            String sortedData = iplObject1.getSortedData(SortFieldIplTeam.STRIKING_RATES);
            IplRunsCsv[] iplRunsCSV = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Andre Russell", iplRunsCSV[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunnsData_WhenCompareSixAndFour_ShouldReturnPlayerHitMaximumSixAndFour() {
        try {
            iplObject1.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            String sortedData = iplObject1.getSortedData(SortFieldIplTeam.SIX_AND_FOUR);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Andre Russell", iplRunsCSVS[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostRunnsData_WhenCompareStrikingRatesAndSixFour_ShouldReturnPalyerMaximumStrikingRatesWithSixFour() {
        try {
            iplObject1.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            String sortedData = iplObject1.getSortedData(SortFieldIplTeam.SRTIKING_RATES_WITH_SIX_FOUR);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("Andre Russell", iplRunsCSVS[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetsOfMostRunnsData_WhenCompareAveargeAndStrikingRates_ShouldReturnPlayerMaximumAveargeWithStrikingRates() {
        try {
            iplObject1.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            String sortedData = iplObject1.getSortedData(SortFieldIplTeam.BAT_AVERAGE_STRIKING_RATES);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("David Warner", iplRunsCSVS[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetMostOfRunnsData_WhenCompareRunsAndAverage_ShouldReturnPlayerMaximumRunWithAverage() {
        try {
            iplObject1.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            String sortedData = iplObject1.getSortedData(SortFieldIplTeam.BAT_RUNS_AVERAGE);
            IplRunsCsv[] iplRunsCSVS = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("David Warner", iplRunsCSVS[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIplWktsCsvFile_ShouldReturnCorrectCount() {
        try {
            int result = iplObject2.loadIplData(IPLFactAnalyserTeam.IPLTeams.BOWLING, IPL_BOWLING_TEAM);
            Assert.assertEquals(4, result);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsData_WhenSortedOnAverage_ShouldReturnTopBowlingAvearages() {
        try {
            iplObject2.loadIplData(IPLFactAnalyserTeam.IPLTeams.BOWLING, IPL_BOWLING_TEAM);
            String sortedData = iplObject2.getSortedData(SortFieldIplTeam.BALL_AVERAGE);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Imran Tahir", iplWktsCsvs[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsData_WhenSortedOn_ShouldReturnTopStrikingRatesOfBowler() {
        try {
            iplObject2.loadIplData(IPLFactAnalyserTeam.IPLTeams.BOWLING, IPL_BOWLING_TEAM);
            String sortedData = iplObject2.getSortedData(SortFieldIplTeam.STRIKING_RATES);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Imran Tahir", iplWktsCsvs[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsData_WhenSorted_ShouldReturnTopEconomyRateOfBowler() {
        try {
            iplObject2.loadIplData(IPLFactAnalyserTeam.IPLTeams.BOWLING, IPL_BOWLING_TEAM);
            String sortedData = iplObject2.getSortedData(SortFieldIplTeam.ECONOMY_RATE);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Deepak Chahar", iplWktsCsvs[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsData_WhenSorted_ShouldReturnMostStrikingRateWithFiveFourWkts() {
        try {
            iplObject2.loadIplData(IPLFactAnalyserTeam.IPLTeams.BOWLING, IPL_BOWLING_TEAM);
            String sortedData = iplObject2.getSortedData(SortFieldIplTeam.STRIKING_RATES_FIVE_FOUR_WKTS);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Imran Tahir", iplWktsCsvs[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostWktsData_WhenSorted_ShouldeReturnMostBowlingAverageWithStikingRates() {
        try {
            iplObject2.loadIplData(IPLFactAnalyserTeam.IPLTeams.BOWLING, IPL_BOWLING_TEAM);
            String sortedData = iplObject2.getSortedData(SortFieldIplTeam.BALL_AVERAGE_STRIKING_RATES);
            IplWktsCsv[] iplWktsCsvs = new Gson().fromJson(sortedData, IplWktsCsv[].class);
            Assert.assertEquals("Imran Tahir", iplWktsCsvs[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }
}







