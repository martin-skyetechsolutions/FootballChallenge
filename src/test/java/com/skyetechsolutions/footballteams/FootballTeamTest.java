package com.skyetechsolutions.footballteams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FootballTeamTest {
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
        dateFormatter = new SimpleDateFormat(dateTemplate);
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