package com.iplfactssheet;

import csvbuilder.CSVBuilderException;

import java.util.Map;

public class IPLRunnsWktsAdapter extends IPLAdapter {

    @Override
    public Map<String, IplTeamDao> loadIplData(String... csvFilePath) throws IPLFactAnalyserException, CSVBuilderException {
        Map<String, IplTeamDao> iplRunsCSVHashMap = super.loadIplData(IplRunsCsv.class,csvFilePath[0]);
        Map<String, IplTeamDao> iplWktsCSVHashMap = super.loadIplData(IplWktsCsv.class, csvFilePath[1]);

        iplWktsCSVHashMap.values().stream()
                .filter(iplTeamDao -> iplRunsCSVHashMap.get(iplTeamDao.player) != null)
                .forEach(iplTeamDao -> iplRunsCSVHashMap.get(iplTeamDao.player).bowlAverage = iplTeamDao.bowlAverage);
        iplWktsCSVHashMap.values().stream()
                .filter(iplTeamDao -> iplRunsCSVHashMap.get(iplTeamDao.player) != null)
                .forEach(iplTeamDao -> iplRunsCSVHashMap.get(iplTeamDao.player).wkts = iplTeamDao.wkts);

        return iplRunsCSVHashMap;
    }
}
