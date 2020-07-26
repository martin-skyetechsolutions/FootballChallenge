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

    private FootballTeamTreeSet<FootballTeam> footballTeams = FootballTeamDataArray.getInstance().footballTeams;

    @GetMapping("/")
    public String index() {
        return "Hello from SkyeTech Solutions!";
    }

    @PostMapping(path="/footballTeam", consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FootballTeam postFootballTeam(@RequestBody FootballTeam footballTeam){
        System.out.println("postbody:\n" + footballTeam);
        System.out.println("before, number of teams: " + footballTeams.size());
        footballTeams.add(footballTeam);
        System.out.println("after, number of teams: " + footballTeams.size());
        return footballTeam;
    }

    @GetMapping(path="/footballTeam", consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FootballTeam postFootballTeam(@RequestBody String name){
        System.out.println("postbody:\n" + name);
        System.out.println("number of teams: " + footballTeams.size());
       FootballTeam result = FootballTeamTreeSetAccessor.getByName(name);
        System.out.println("team: " + result);
        return footballTeams.first();
    }
}
