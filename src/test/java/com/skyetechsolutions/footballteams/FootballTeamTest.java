package com.skyetechsolutions.footballteams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FootballTeamTest {
    private final String name="Arsenal";
    private final String city="London";
    private final String owner="Martin Golding";
    private final int stadiumCapacity=70000;
    private final String competition="Premier League";
    private final int numberOfPlayers=20;
    private final Date dateOfCreation= new SimpleDateFormat("dd/MM/yyy").parse("30/12/1976");
    private FootballTeam testTeam;

    FootballTeamTest() throws ParseException {
    }

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
    void getName() {
        assertEquals(name, testTeam.getName());
    }

    @Test
    void getCity() {
        assertEquals(city, testTeam.getCity());
    }

    @Test
    void getOwner() {
        assertEquals(owner, testTeam.getOwner());
    }

    @Test
    void getStadiumCapacity() {
        assertEquals(stadiumCapacity, testTeam.getStadiumCapacity());
    }

    @Test
    void getCompetition() {
        assertEquals(competition, testTeam.getCompetition());
    }

    @Test
    void getNumberOfPlayers() {
        assertEquals(numberOfPlayers, testTeam.getNumberOfPlayers());
    }

    @Test
    void getDateOfCreation() {
            assertEquals(0, dateOfCreation.compareTo(testTeam.getDateOfCreation()));

    }

    @Test
    void testToString() {
        assertEquals(
                "FootballTeam{" +
                        "name='" + name + '\'' +
                        ", city='" + city + '\'' +
                        ", owner='" + owner + '\'' +
                        ", stadiumCapacity=" + stadiumCapacity +
                        ", competition='" + competition + '\'' +
                        ", numberOfPlayers=" + numberOfPlayers +
                        ", dateOfCreation=" + dateOfCreation + "}",
            testTeam.toString());
    }
}