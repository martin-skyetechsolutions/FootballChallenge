package com.skyetechsolutions.footballteams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {
    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${skyetech.activemq.queue}")
    String queue;

    public void send(FootballTeam footballteam){
        jmsTemplate.convertAndSend(queue, footballteam);
    }
}
