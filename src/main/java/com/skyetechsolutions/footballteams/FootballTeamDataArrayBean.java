package com.skyetechsolutions.footballteams;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FootballTeamDataArrayBean {
    @Bean
    public FootballTeamDataArray FootballTeams(){
        return FootballTeamDataArray.getInstance();
    }
}
