package com.iplfactssheet;

public class IplRunnsDao {

    public String player;
    public int matchs;
    public int innings;
    public int runs;
    public double average;

    public IplRunnsDao(IPLRunsCSV iplRunsCSV) {
        player = iplRunsCSV.player;
        matchs = Integer.parseInt(iplRunsCSV.matchs);
        innings = Integer.parseInt(iplRunsCSV.innings);
        runs = Integer.parseInt(iplRunsCSV.runs);
        if(iplRunsCSV.average.contains("-")) {
            average = 0;
        }
        if(!iplRunsCSV.average.contains("-")){
            average = Double.parseDouble(iplRunsCSV.average);
        }
    }

   public IPLRunsCSV getIPLRunns(){
        return new IPLRunsCSV(player,average,runs,matchs,innings);
   }

    public Object getDTO() {
        return new IPLRunsCSV((String) player,(double)average,(int)runs,(int)matchs,(int)innings);
    }
}
