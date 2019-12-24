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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MokitoIplFactAnalyserTest {

    String IPL_BATTING_TEAM = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostRuns.csv";

    IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();

    @Before
    public void testIplRunnsAdapter() {
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
            iplFactAnalyserTeam.setIplAdapter(iplRunnsAdapter);
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
            int result = iplFactAnalyserTeam.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            Assert.assertEquals(5, result);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLFactsSheetOfMostRunsSampleData_WhenSortedOnAverage_ShouldReturnTopBattingAvearages() {
        try {
            iplFactAnalyserTeam.loadIplData(IPLFactAnalyserTeam.IPLTeams.BATTING, IPL_BATTING_TEAM);
            String sortedData = iplFactAnalyserTeam.getSortedData(SortFieldIplTeam.AVERAGE);
            IplRunsCsv[] iplRunsCSV = new Gson().fromJson(sortedData, IplRunsCsv[].class);
            Assert.assertEquals("MS Dhoni", iplRunsCSV[0].player);
        } catch (IPLFactAnalyserException | CSVBuilderException e) {
        }
    }

}







