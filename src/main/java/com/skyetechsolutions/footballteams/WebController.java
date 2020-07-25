package com.skyetechsolutions.footballteams;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.skyetechsolutions.footballteams.JmsConsumer;

import java.util.TreeSet;

import static org.springframework.http.MediaType.*;

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


//    @PostMapping(path="/footballTeam", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(path="/footballTeam", consumes = APPLICATION_JSON_VALUE)
    public FootballTeam postFootballTeam(@RequestBody FootballTeam footballTeam){
    //public FootballTeam postFootballTeam(@RequestBody(required = true) FootballTeam footballTeam){
        jmsProducer.send(footballTeam);
        return footballTeam;
    }

    @PostMapping(path="/footballTeamTest")
    public FootballTeam postFootballTeam(@RequestBody String input) throws JsonProcessingException {
        //public FootballTeam postFootballTeam(@RequestBody(required = true) FootballTeam footballTeam){
        ObjectMapper Mapper = new ObjectMapper();
        FootballTeam footballTeam = Mapper.readValue(input, FootballTeam.class);
        jmsProducer.send(footballTeam);
        return footballTeam;
    }

    @PostMapping(path="/personTest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public person postPerson(@RequestBody person peeps) throws JsonProcessingException {
        System.out.println("post:\n" + peeps);
        return peeps;
    }
}
