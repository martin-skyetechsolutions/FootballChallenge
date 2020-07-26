package com.skyetechsolutions.footballteams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static com.skyetechsolutions.footballteams.FootballTeamTreeSetAccessor.getAllTeams;
import static com.skyetechsolutions.footballteams.FootballTeamTreeSetAccessor.getAllTeamsSortByCapacity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class WebController {


    private FootballTeamTreeSet<FootballTeam> footballTeams = FootballTeamDataArray.getInstance().footballTeams;

    Logger logger = LoggerFactory.getLogger(WebController.class);

    @GetMapping("/health")
    public String awsHealthCheck() {
        logger.info("local logger -> GET /health");
        return "{\"status\":\"up\"}";
    }

    @GetMapping("/hello")
    public String index() {
        logger.info("local logger -> GET /");
        return "Hello from SkyeTech Solutions!";
    }

    @PostMapping(path="/footballTeam", consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FootballTeam postFootballTeam(@RequestBody FootballTeam footballTeam){
        int numberOfTeams = footballTeams.size();
        footballTeams.add(footballTeam);
        String message = "local logger -> POST /footballTeam, body: " + footballTeam + ", number of teams; before: " + numberOfTeams + " after: " + footballTeams.size();
        if(footballTeams.size() > numberOfTeams){
            logger.info(message);
        }else{
            logger.warn(message + " Team Not added" );
        }
        return footballTeam;
    }

    @GetMapping(path="/footballTeam", consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FootballTeam getFootballTeam(@RequestBody String name){
        FootballTeam result = FootballTeamTreeSetAccessor.getByName(name);
        if(!result.equals(null)){
            logger.info("local logger -> GET /footballTeams, result: " + result );
        }else{
            logger.warn("local logger -> GET /footballTeams, result: No data" );
        }
        return result;
    }

    @GetMapping(path="/footballTeams", consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FootballTeamTreeSet<FootballTeam> getFootballTeams(){
        FootballTeamTreeSet<FootballTeam> result = getAllTeams();
        if(!result.isEmpty()){
            logger.info("local logger -> GET /footballTeams, result: " + result );
        }else{
            logger.warn("local logger -> GET /footballTeams, result: No data" );
        }

        return result;
    }

    @GetMapping(path="/footballTeamsSortedByCapacity", consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<FootballTeam> getFootballTeamsSortedByCapacity(){
        ArrayList<FootballTeam> result = getAllTeamsSortByCapacity();
        if(!result.isEmpty()){
            logger.info("local logger -> GET /footballTeamsSortedByCapacity, result: " + result );
        }else{
            logger.warn("local logger -> GET /footballTeamsSortedByCapacity, result: No data" );
        }
        return result;
    }
}
