package com.greenfox.p2pchat.controller;

import com.greenfox.p2pchat.model.Client;
import com.greenfox.p2pchat.model.OkResponse;
import com.greenfox.p2pchat.model.ReceivedMessage;
import com.greenfox.p2pchat.model.UserMessage;
import com.greenfox.p2pchat.service.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin("*")
public class ReceiveController {

  private final String uri = System.getenv("CHAT_APP_PEER_ADDRESS") + "/api/message/receive";
  private final String clientID = System.getenv("CHAT_APP_UNIQUE_ID");

  @Autowired
  MessageRepository messageRepo;

  @RequestMapping(value = "/api/message/receive", method = RequestMethod.POST)
  public Object receive(@RequestBody ReceivedMessage receivedMessage) {
    ReceivedMessage received = new ReceivedMessage(receivedMessage.getMessage(), receivedMessage.getClient());
    UserMessage chatMessage = new UserMessage(received.getMessage().getId(),
        received.getMessage().getUserName(), received.getMessage().getMessage(),
        received.getMessage().getTimestamp());
    messageRepo.save(chatMessage);
    if(!receivedMessage.getClient().getId().equals(clientID)) {
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.postForObject(uri, received, ReceivedMessage.class);
    }
    return new OkResponse("ok");
  }
}
