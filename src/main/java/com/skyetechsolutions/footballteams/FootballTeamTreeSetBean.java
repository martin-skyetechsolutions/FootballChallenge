package com.skyetechsolutions.footballteams;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FootballTeamTreeSetBean {
    @Bean
    public FootballTeamTreeSet footballTeamTreeSet(){
        return new FootballTeamTreeSet();
    }
}

