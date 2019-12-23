package com.iplfactssheet;

import csvbuilder.CSVBuilderException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MokitoIplAdapterTest {

    String IPL_BATTING_TEAM = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostRuns.csv";
    String IPL_BOWLING_TEAM = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Mock
    IPLAdapter iplAdapter;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIplAdapter() throws CSVBuilderException, IPLFactAnalyserException {
        HashMap<String, IplTeamDao> iplTeamDaoHashMap = new HashMap<>();
        IplTeamDao iplTeamDao = new IplTeamDao("Gambhir", 2, 5, 2.12, 2, 1, 2.6, 6,
                5, 3, 56.5, 2.2, 5.21, 120,
                130);
        iplTeamDaoHashMap.put(String.valueOf(IPLFactAnalyserTeam.IPLTeams.BATTING), iplTeamDao);
        iplTeamDaoHashMap.put(String.valueOf(IPLFactAnalyserTeam.IPLTeams.BATTING), iplTeamDao);
        IPLAdapter iplAdapter = mock(IPLAdapter.class);
        when(iplAdapter.loadIplData(IplTeamDao.class, IPL_BATTING_TEAM)).thenReturn(iplTeamDaoHashMap);
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
        iplFactAnalyserTeam.setIplAdapter(iplAdapter);
        int result = iplFactAnalyserTeam.loadIplData(IPL_BATTING_TEAM);
        Assert.assertEquals(1, iplTeamDaoHashMap.size());

    }
}
