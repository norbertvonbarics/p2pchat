package com.greenfox.p2pchat.controller;

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

@RestController
@CrossOrigin("*")
public class ReveiveController {


  @Autowired
  MessageRepository messageRepo;

  @RequestMapping(value = "/api/message/receive", method = RequestMethod.POST)
  public Object receive(@RequestBody ReceivedMessage receivedMessage) {
    ReceivedMessage received = new ReceivedMessage(receivedMessage.getMessage(), receivedMessage.getClient());
    UserMessage chatMessage = new UserMessage(received.getMessage().getUserName(),
        received.getMessage().getMessage());
    messageRepo.save(chatMessage);
    return new OkResponse();
  }
}
