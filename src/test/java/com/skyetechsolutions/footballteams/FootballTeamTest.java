package com.skyetechsolutions.footballteams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FootballTeamTest {
    private final String name="Arsenal";
    private final String city="London";
    private final String owner="Martin Golding";
    private final int stadiumCapacity=70000;
    private final String competition="Premier League";
    private final int numberOfPlayers=20;
    private final String dateOfCreation="30/12/1976";
    private FootballTeam testTeam;

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
        String dateTemplate = "dd/MM/yyyy";
        DateFormat dateFormatter = new SimpleDateFormat(dateTemplate);
        try {
            Date testDate = dateFormatter.parse(dateOfCreation);
            assertEquals(0, testDate.compareTo(testTeam.getDateOfCreation()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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