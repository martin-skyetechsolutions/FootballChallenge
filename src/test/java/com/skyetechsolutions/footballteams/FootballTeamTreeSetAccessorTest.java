package com.skyetechsolutions.footballteams;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class FootballTeamTreeSetAccessorTest {
    private String name="Arsenal";
    private String city="London";
    private String owner="Martin Golding";
    private int stadiumCapacity=70000;
    private String competition="Premier League";
    private int numberOfPlayers=20;
    private String dateOfCreation="30/12/1976";
    private FootballTeam testTeam;
    private String dateTemplate = "dd/MM/yyyy";

    @Test
    void getByNameFailsOnNonExistentTeam() {
        FootballTeamDataArray testFootballTeamDataArray = FootballTeamDataArray.getInstance();
        testFootballTeamDataArray.footballTeams.clear();
        assertNull(FootballTeamTreeSetAccessor.getByName("Everton"));
    }

    @Test
    void getByName(){
        FootballTeamDataArray testFootballTeamDataArray = FootballTeamDataArray.getInstance();
        testFootballTeamDataArray.footballTeams.clear();
        assertEquals(0, testFootballTeamDataArray.footballTeams.size());
        try {
            FootballTeam newTestTeam = new FootballTeam("Everton","Liverpool","Our Kid",50000,"Premier League",18,new SimpleDateFormat(dateTemplate).parse("25/12/1900"));
            testFootballTeamDataArray.footballTeams.add(newTestTeam);
            FootballTeam actual = FootballTeamTreeSetAccessor.getByName("Everton");
            assertEquals(newTestTeam,actual);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getByNameTwoItemsInSet(){
        FootballTeamDataArray testFootballTeamDataArray = FootballTeamDataArray.getInstance();
        testFootballTeamDataArray.footballTeams.clear();
        assertEquals(0, testFootballTeamDataArray.footballTeams.size());
        try {
            FootballTeam newTestTeam = new FootballTeam("Everton","Liverpool","Our Kid",50000,"Premier League",18,new SimpleDateFormat(dateTemplate).parse("25/12/1900"));
            testFootballTeamDataArray.footballTeams.add(newTestTeam);
            FootballTeam anotherTestTeam = new FootballTeam(name,city,owner,stadiumCapacity,competition,numberOfPlayers,new SimpleDateFormat(dateTemplate).parse(dateOfCreation));
            FootballTeam actual = FootballTeamTreeSetAccessor.getByName("Everton");
            assertEquals(newTestTeam,actual);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}