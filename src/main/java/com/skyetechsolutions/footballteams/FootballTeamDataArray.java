package com.skyetechsolutions.footballteams;

import java.util.TreeSet;

//Singleton pattern;
public class FootballTeamDataArray {
    private static FootballTeamDataArray INSTANCE = null;
    public TreeSet<FootballTeam> footballTeams = new TreeSet<>();

    private FootballTeamDataArray(){
    }

    public static FootballTeamDataArray getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new FootballTeamDataArray();
        }
        return INSTANCE;
    }
}
