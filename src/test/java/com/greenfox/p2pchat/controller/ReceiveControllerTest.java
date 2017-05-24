package com.greenfox.p2pchat.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.p2pchat.P2pchatApplication;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.greenfox.p2pchat.model.Client;
import com.greenfox.p2pchat.model.ReceivedMessage;
import com.greenfox.p2pchat.model.UserMessage;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = P2pchatApplication.class)
@WebAppConfiguration
@EnableWebMvc
public class ReceiveControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      Charset.forName("utf8"));

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testReceive_withCorrectInput() throws Exception {
    UserMessage message = new UserMessage(7655482, "EggDice", "How you doin'?", new Timestamp(1322018752992L));
     Client client = new Client("EggDice");
    ReceivedMessage receivedMessage = new ReceivedMessage(message, client);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(receivedMessage);

    mockMvc.perform(post("/api/message/receive")
        .contentType(contentType)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("ok")));
  }

  private String requestString = "{\n"
      + "  \"message\": {\n"
      + "    \"id\": 7655482,\n"
      + "    \"username\": \"EggDice\",\n"
      + "    \"text\": \"How you doin'?\",\n"
      + "    \"timestamp\": 1322018752992\n"
      + "  },\n"
      + "  \"client\": {\n"
      + "    \"id\": \"EggDice\"\n"
      + "  }\n"
      + "}";
  private String requestIsOkString = "{\n"
      + "  \"status\": \"ok\"\n"
      + "}";

  @Test
  public void testAddUsername() throws Exception {
    mockMvc.perform(post("/api/message/receive")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestString))
        .andExpect(status().isOk())
        .andExpect(content().json(requestIsOkString));
  }

  @Test
  public void testMissingBodyParameterOnGettingMessage() throws Exception {
    mockMvc.perform(post("/api/message/receive")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n"
            + "  \"message\": {\n"
            + "    \"id\": 7655482,\n"
            + "    \"text\": \"How you doin'?\",\n"
            + "    \"timestamp\": 1322018752992\n"
            + "  },\n"
            + "  \"client\": {\n"
            + "    \"id\": \"EggDice\"\n"
            + "  }\n"
            + "}"))
        .andExpect(status().is4xxClientError())
        .andExpect(content().json("{\n"
            + "  \"status\": \"error\",\n"
            + "  \"message\": \"Missing field(s): message.chatUserName, \"\n"
            + "}"));
  }
}