package com.iplfactssheet;

public class IPLTeamFactory {

    public static IPLAdapter getIPLData(IPLFactAnalyserTeam.IPLTeams iplTeams) throws IPLFactAnalyserException {
        if (iplTeams.equals(IPLFactAnalyserTeam.IPLTeams.BATTING)) {
            return new IPLRunnsAdapter();
        } else if (iplTeams.equals(IPLFactAnalyserTeam.IPLTeams.BOWLING)) {
            return new IPLWktsAdapter();
        }else if (iplTeams.equals(IPLFactAnalyserTeam.IPLTeams.BATTING_BOWLING)){
            return new IPLRunnsWktsAdapter();
        }
        throw new IPLFactAnalyserException("Unkonwn Input", IPLFactAnalyserException.ExceptionType.INVALID_INPUT);
    }
}
