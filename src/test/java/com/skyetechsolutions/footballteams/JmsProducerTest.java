package com.skyetechsolutions.footballteams;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JmsProducerTest {

    @Autowired
    ConnectionFactory ConnectionFactory;
    String queueName = "testQueue";
    MessageProducer jmsAmqProducer;
    Destination jmsAmqDestination;
    Session jmsAmqSession;
    Connection jmsAmqConnection;

    JmsProducerTest() throws ParseException {
    }

    @Before
    public void setupJmsSession() throws JMSException {
        jmsAmqConnection = ConnectionFactory.createConnection();
        jmsAmqConnection.start();
        jmsAmqSession = jmsAmqConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        jmsAmqDestination = jmsAmqSession.createQueue(queueName);
        jmsAmqProducer = jmsAmqSession.createProducer(jmsAmqDestination);
        jmsAmqProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }

    @After
    public void tearDownJmsSession() throws JMSException {
        jmsAmqSession.close();
        jmsAmqConnection.close();
    }


    @Test
    void send() throws ParseException, JMSException {
        TextMessage msg = jmsAmqSession.createTextMessage("hello");
        jmsAmqProducer.send(jmsAmqDestination,msg);
    }
}