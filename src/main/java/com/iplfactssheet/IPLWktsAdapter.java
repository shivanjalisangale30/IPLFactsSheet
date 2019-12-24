package com.iplfactssheet;

import csvbuilder.CSVBuilderException;
import java.util.Map;

public class IPLWktsAdapter extends IPLAdapter {

    @Override
    public Map<String, IplTeamDao> loadIplData(IPLFactAnalyserTeam.IPLTeams iplTeams, String... csvFilePath) throws IPLFactAnalyserException, CSVBuilderException {
        return loadIplData(IplWktsCsv.class,csvFilePath);
    }
}
