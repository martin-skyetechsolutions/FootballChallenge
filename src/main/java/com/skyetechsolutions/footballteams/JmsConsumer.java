package com.skyetechsolutions.footballteams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.TreeSet;

@Component
public class JmsConsumer {

    @Autowired
    private TreeSet<FootballTeam> footballTeams = FootballTeamDataArray.getInstance().footballTeams;

    @JmsListener(destination = "${skyetech.activemq.queue}", containerFactory="jsaFactory")
    public void receive(FootballTeam input){
        System.out.println("Recieved Message: " + input);
        footballTeams.add(input);
    }
}
