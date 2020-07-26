package com.skyetechsolutions.footballteams;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebControllerTest {
    private  String name="Arsenal";
    private  String city="London";
    private  String owner="Martin Golding";
    private  int stadiumCapacity=70000;
    private  String competition="Premier League";
    private  int numberOfPlayers=20;
    private Date dateOfCreation= new SimpleDateFormat("dd/MM/yyy").parse("30/12/1976");
    private FootballTeam testTeam;


    @Autowired
    private MockMvc mvc;

    WebControllerTest() throws ParseException {
    }

    @Test
    void getIndex() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello from SkyeTech Solutions!")));
    }

    @Test
    public void postFootballTeam() throws Exception {
        FootballTeam testData = new FootballTeam(
                name,
                city,
                owner,
                stadiumCapacity,
                competition,
                numberOfPlayers,
                dateOfCreation);

        ObjectMapper Mapper = new ObjectMapper();
        String testString = Mapper.writeValueAsString(testData);
        System.out.println("testData:\n" + testString);

        this.mvc.perform(post("/footballTeam")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(testString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(equalTo(Mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writeValueAsString(testData))))
        ;
    }

    @Test
    public void getFootballTeam() throws Exception {
        FootballTeam testData = new FootballTeam(
                name,
                city,
                owner,
                stadiumCapacity,
                competition,
                numberOfPlayers,
                dateOfCreation);

        ObjectMapper Mapper = new ObjectMapper();
        String testString = Mapper.writeValueAsString(testData);
        System.out.println("testData:\n" + testString);

        this.mvc.perform(post("/footballTeam")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(testString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(equalTo(Mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writeValueAsString(testData))));

        mvc.perform(MockMvcRequestBuilders.get("/footballTeam")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(name))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(equalTo(Mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writeValueAsString(testData))));
    }

    @Test
    public void getFootballTeamsSortedByCapacity() throws Exception {
        FootballTeam testData = new FootballTeam(
                name,
                city,
                owner,
                stadiumCapacity,
                competition,
                numberOfPlayers,
                dateOfCreation);

        ObjectMapper Mapper = new ObjectMapper();
        FootballTeamDataArray.getInstance().footballTeams.clear();

        LinkedList<FootballTeam> expected = new LinkedList<>();
        for(int i=0; i<100; i++) {
            name = RandomString.generate(10);
            stadiumCapacity = RandomInt.generate();
            expected.add( new FootballTeam(name, city, owner, stadiumCapacity, competition, numberOfPlayers, dateOfCreation));

            this.mvc.perform(post("/footballTeam")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(Mapper.writeValueAsString(expected.getLast())))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(content().string(equalTo(Mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writeValueAsString(expected.getLast()))));

        }

        expected = expected.stream()
                .sorted(Comparator.comparingInt(FootballTeam::getStadiumCapacity)
                    .reversed()
                .thenComparing(FootballTeam::getName))
                .collect(Collectors.toCollection(LinkedList<FootballTeam>::new));

        for(FootballTeam i: expected){
            System.out.println(i.getStadiumCapacity());
        }

        mvc.perform(MockMvcRequestBuilders.get("/footballTeamsSortedByCapacity")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(equalTo(Mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writeValueAsString(expected))));
    }

}