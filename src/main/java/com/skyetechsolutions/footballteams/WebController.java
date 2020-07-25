package com.skyetechsolutions.footballteams;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeSet;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class WebController {

    @Autowired
    JmsProducer jmsProducer;

    @Autowired
    private TreeSet<FootballTeam> footballTeams = FootballTeamDataArray.getInstance().footballTeams;

    @GetMapping("/")
    public String index() {
        return "Greetings from SkyeTech Solutions!";
    }

    @PostMapping(path="/footballTeam", consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FootballTeam postFootballTeam(@RequestBody FootballTeam footballTeam){
        System.out.println("post:\n" + footballTeam);
        jmsProducer.send(footballTeam);
        return footballTeam;
    }

    @PostMapping(path="/personTest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public person postPerson(@RequestBody person peeps) throws JsonProcessingException {
        return peeps;
    }
}
