package com.iplfactssheet;

import java.util.Comparator;

public class BatBAllAverageComparator implements Comparator<IplTeamDao> {
    @Override
    public int compare(IplTeamDao iplTeamDaoOne, IplTeamDao iplTeamDaoTwo) {
        return (int)((iplTeamDaoOne.batAaverage+iplTeamDaoTwo.bowlAverage)-
                     (iplTeamDaoTwo.batAaverage+iplTeamDaoOne.bowlAverage));
    }
}
