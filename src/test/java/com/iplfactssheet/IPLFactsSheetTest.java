package com.iplfactssheet;

import org.junit.Assert;
import org.junit.Test;

public class IPLFactsSheetTest {

    String IPL_BATTING_TEAM = "/home/admin1/Desktop/IPL/src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenIPLFactsSheetOfMostRuns_WhenProper_ShouldGiveCorrectCount() {
        IPLFactAnalyserTeam iplFactAnalyserTeam = new IPLFactAnalyserTeam();
        try {
            int result = iplFactAnalyserTeam.loadBattingTeamData(IPL_BATTING_TEAM);
             Assert.assertEquals(101,result);
        } catch (IPLFactAnalyserException e) {}
    }
}
