package com.skyetechsolutions.footballteams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
    private FootballTeamDataArray testFootballTeamDataArray = FootballTeamDataArray.getInstance();

    @BeforeEach
    void createObjectAndClear() {
        testFootballTeamDataArray.footballTeams.clear();
    }

    @Test
    void getByName_FailsOnNonExistentTeam() {
        assertNull(FootballTeamTreeSetAccessor.getByName("Everton"));
    }

    @Test
    void getByName(){
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
    void getByName_TwoItemsInSet(){
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


    @Test
    void getAllTeams_returnNullForEmptySet() {
        assertEquals("[]", FootballTeamTreeSetAccessor.getAllTeams().toString());
    }

    @Test
    void getAllTeamsSortByCapacity_1Item(){
        try {
            FootballTeam newTestTeam = new FootballTeam("Everton","Liverpool","Our Kid",50000,"Premier League",18,new SimpleDateFormat(dateTemplate).parse("25/12/1900"));
            testFootballTeamDataArray.footballTeams.add(newTestTeam);
            ArrayList<FootballTeam> result = FootballTeamTreeSetAccessor.getAllTeamsSortByCapacity();
            assertEquals(1, result.size());
            assertEquals(newTestTeam,result.get(0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllTeamsSortByCapacity_2Items(){
        try {
            FootballTeam newTestTeam1 = new FootballTeam("Everton","Liverpool","Our Kid",50000,"Premier League",18,new SimpleDateFormat(dateTemplate).parse("25/12/1900"));
            testFootballTeamDataArray.footballTeams.add(newTestTeam1);
            FootballTeam newTestTeam2 = new FootballTeam(name, city, owner, stadiumCapacity, competition, numberOfPlayers, new SimpleDateFormat(dateTemplate).parse(dateOfCreation));
            testFootballTeamDataArray.footballTeams.add(newTestTeam2);
            ArrayList<FootballTeam> result = FootballTeamTreeSetAccessor.getAllTeamsSortByCapacity();
            System.out.println("getAllTeamsSortByCapacity_2Items\n" + result);
            assertEquals(2, result.size());
            assertEquals(newTestTeam2,result.get(0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllTeamsSortByCapacity_MultipleItems(){
        try {
            FootballTeam newTestTeam;
            for(int i=0; i<100; i++) {
                name = RandomString.generate(10);
                stadiumCapacity = RandomInt.generate();
                newTestTeam = new FootballTeam(name, city, owner, stadiumCapacity, competition, numberOfPlayers, new SimpleDateFormat(dateTemplate).parse(dateOfCreation));
                testFootballTeamDataArray.footballTeams.add(newTestTeam);
            }
            ArrayList<FootballTeam> result = FootballTeamTreeSetAccessor.getAllTeamsSortByCapacity();
            int lastInt = RandomInt.range * RandomInt.multiplier + 1;
            for(FootballTeam t: result){
                System.out.println(t.getName() + ": " + t.getStadiumCapacity());
                assertTrue(t.getStadiumCapacity() <= lastInt);
                lastInt = t.getStadiumCapacity();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}