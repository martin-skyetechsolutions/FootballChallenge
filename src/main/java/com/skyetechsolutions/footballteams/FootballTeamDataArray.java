package com.skyetechsolutions.footballteams;

//Singleton pattern;

//TODO make this thread safe
public class FootballTeamDataArray {
    private static FootballTeamDataArray INSTANCE = null;
    public FootballTeamTreeSet<FootballTeam> footballTeams = new FootballTeamTreeSet<>();

    private FootballTeamDataArray(){
    }

    public static FootballTeamDataArray getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new FootballTeamDataArray();
        }
        return INSTANCE;
    }
}
