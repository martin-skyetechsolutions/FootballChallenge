package com.skyetechsolutions.footballteams;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class FootballTeamDataArrayTest {
    private String name="Arsenal";
    private String city="London";
    private String owner="Martin Golding";
    private int stadiumCapacity=70000;
    private String competition="Premier League";
    private int numberOfPlayers=20;
    private String dateOfCreation="30/12/1976";
    private FootballTeam testTeam;
    private DateFormat dateFormatter;
    private String dateTemplate = "dd/MM/yyyy";

    @Test
    void getInstance() {
        FootballTeamDataArray testFootballTeamDataArray = FootballTeamDataArray.getInstance();
        assertNotNull(testFootballTeamDataArray);
    }

    @Test
    void addTeam(){
        FootballTeamDataArray testFootballTeamDataArray = FootballTeamDataArray.getInstance();
        testFootballTeamDataArray.footballTeams.clear();
        assertEquals(0,testFootballTeamDataArray.footballTeams.size());
        try {
            FootballTeam testTeam = new FootballTeam("Everton","Liverpool","Our Kid",50000,"Premier League",18,"25/12/1900");
        } catch (ParseException e) {
            e.printStackTrace();
        }
       // assertEquals(1,testTeamArray.footballTeams.size());
    }


}