package com.skyetechsolutions.footballteams;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.skyetechsolutions.footballteams.FootballTeam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Objects;

//@EnableWebMvc
@SpringBootTest
@AutoConfigureMockMvc
public class WebControllerTest {

	private  String name="Arsenal";
	private  String city="London";
	private  String owner="Martin Golding";
	private  int stadiumCapacity=70000;
	private  String competition="Premier League";
	private  int numberOfPlayers=20;
	private  String dateOfCreation="30/12/1976";
	private FootballTeam testTeam;


	@Autowired
	private MockMvc mvc;

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/")
			.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Greetings from SkyeTech Solutions!")));
	}

	@Test
	public void postFootballTeamTest() throws Exception {

		FootballTeam testData = new FootballTeam(
				name,
				city,
				owner,
				stadiumCapacity,
				competition,
				numberOfPlayers,
				dateOfCreation);

		ObjectMapper Mapper = new ObjectMapper();
		System.out.println("testData:\n" + Mapper.writeValueAsString(testData));

		mvc.perform(post("/footballTeamTest")
				.accept(MediaType.APPLICATION_JSON)
				.content(Mapper.writeValueAsString(testData)))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentType("application/json"))
		;
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
		System.out.println("testData:\n" + Mapper.writeValueAsString(testData));

		mvc.perform(post("/footballTeam")
			.content(Mapper.writeValueAsString(testData)))
			.andExpect(status().isOk())
				/*.andExpect(content()
						.contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(equalToObject(testData )))*/
		 ;
	}

	@Test
	public void postPerson() throws Exception {
		person peeps = new person("Martin", 21);

		ObjectMapper Mapper = new ObjectMapper();
		System.out.println("testData:\n" + Mapper.writeValueAsString(peeps));

		this.mvc.perform(post("/personTest")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(Mapper.writeValueAsString(peeps)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string(equalTo(Mapper.writeValueAsString(peeps))))
		;
	}

}
