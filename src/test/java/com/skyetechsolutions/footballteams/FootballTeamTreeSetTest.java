package com.skyetechsolutions.footballteams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FootballTeamTreeSetTest {
    private String name="Arsenal";
    private String city="London";
    private String owner="Martin Golding";
    private int stadiumCapacity=70000;
    private String competition="Premier League";
    private int numberOfPlayers=20;
    private String dateTemplate = "dd/MM/yyyy";
    private Date dateOfCreation= new SimpleDateFormat(dateTemplate).parse("30/12/1976");
    private FootballTeam testTeam;
    private FootballTeamTreeSet<FootballTeam> testSet;

    FootballTeamTreeSetTest() throws ParseException {
    }

    FootballTeam createTeam() throws ParseException {
        FootballTeam newTeam = new FootballTeam(
                name,
                city,
                owner,
                stadiumCapacity,
                competition,
                numberOfPlayers,
                dateOfCreation);

        System.out.println("newTeam:\n" + newTeam.toString());
        return newTeam;
    }

    @BeforeEach
    void createSetAmdTeam() throws ParseException {
        testSet = new FootballTeamTreeSet<>();
        testTeam = createTeam();
    }


    @Test
    void addTeam(){
        testSet.clear();
        assertFalse(testSet.contains(testTeam));
        testSet.add(testTeam);
       assertTrue(testSet.contains(testTeam));
    }

    @Test
    void doesNotAddDuplicateTeam(){
        testSet.clear();
        testSet.add(testTeam);
        assertEquals(1,testSet.size());
        testSet.add(testTeam);
        assertEquals(1,testSet.size());
    }

    @Test
    void addTwoTeams() throws ParseException {
        testSet.clear();
        testSet.add(testTeam);
        assertEquals(1,testSet.size());
        name = RandomString.generate(10);
        FootballTeam newTeam = createTeam();
        testSet.add(newTeam);
        assertEquals(2,testSet.size());
    }
}