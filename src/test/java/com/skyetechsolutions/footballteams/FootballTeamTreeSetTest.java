package com.skyetechsolutions.footballteams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class FootballTeamTreeSetTest {
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

    @BeforeEach
    void createTeam() throws ParseException {
        testTeam = new FootballTeam(
                name,
                city,
                owner,
                stadiumCapacity,
                competition,
                numberOfPlayers,
                dateOfCreation);
    }

    @Test
    void addTeam(){
        FootballTeamTreeSet<FootballTeam> testSet = new FootballTeamTreeSet<>();
        assertFalse(testSet.contains(testTeam));
        testSet.add(testTeam);
       assertTrue(testSet.contains(testTeam));
    }

    @Test
    void doesNotAddDuplicateTeam(){
        FootballTeamTreeSet<FootballTeam> testSet = new FootballTeamTreeSet<>();
        testSet.clear();
        testSet.add(testTeam);
        assertEquals(1,testSet.size());
        testSet.add(testTeam);
        assertEquals(1,testSet.size());
    }
}