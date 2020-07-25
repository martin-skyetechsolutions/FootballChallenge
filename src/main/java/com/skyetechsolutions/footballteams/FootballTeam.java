package com.skyetechsolutions.footballteams;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


// Need comparable and compareTo override for custom contains and add in TreeSet.
public class FootballTeam implements Comparable<FootballTeam>{
    private String name;
    private String city;
    private String owner;
    private int stadiumCapacity;
    private String competition;
    private int numberOfPlayers;
    private Date dateOfCreation;
    private DateFormat dateFormatter;
    private String dateTemplate = "dd/MM/yyyy";

    public FootballTeam(String name, String city, String owner, int stadiumCapacity, String competition, int numberOfPlayers, Date dateOfCreation) throws ParseException {
        this.name = name;

        this.city = city;
        this.owner = owner;
        this.stadiumCapacity = stadiumCapacity;
        this.competition = competition;
        this.numberOfPlayers = numberOfPlayers;
        this.dateFormatter = new SimpleDateFormat(dateTemplate);
        this.dateOfCreation = dateOfCreation;
    }

    public int compareTo(FootballTeam o){
        return this.getName().compareTo(o.getName());
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getOwner() {
        return owner;
    }

    public int getStadiumCapacity() {
        return stadiumCapacity;
    }

    public String getCompetition() {
        return competition;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    @Override
    public String toString() {
        return "FootballTeam{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", owner='" + owner + '\'' +
                ", stadiumCapacity=" + stadiumCapacity +
                ", competition='" + competition + '\'' +
                ", numberOfPlayers=" + numberOfPlayers +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
