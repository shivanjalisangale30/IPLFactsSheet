package com.iplfactssheet;

import java.util.Comparator;

public class BatBAllAverageComparator implements Comparator<IplTeamDao> {
    @Override
    public int compare(IplTeamDao iplTeamDaoOne, IplTeamDao iplTeamDaoTwo) {

        return (int)((iplTeamDaoOne.batAaverage+(1d/iplTeamDaoTwo.bowlAverage))-
                     (iplTeamDaoTwo.batAaverage+(1d/iplTeamDaoOne.bowlAverage)));
    }
}
