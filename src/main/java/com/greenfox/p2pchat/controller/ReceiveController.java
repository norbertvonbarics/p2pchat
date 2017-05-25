package com.greenfox.p2pchat.controller;

import com.greenfox.p2pchat.ErrorMessage;
import com.greenfox.p2pchat.P2pchatApplication;
import com.greenfox.p2pchat.model.Response;
import com.greenfox.p2pchat.model.ReceivedMessage;
import com.greenfox.p2pchat.model.UserMessage;
import com.greenfox.p2pchat.repository.MessageRepository;
import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin("*")
public class ReceiveController {

  private final String url = System.getenv("CHAT_APP_PEER_ADDRESSS") + "/api/message/receive";
  private final String clientID = System.getenv("CHAT_APP_UNIQUE_ID");


  Logger logger = Logger.getLogger(P2pchatApplication.class.getName());

  @Autowired
  MessageRepository messageRepo;

  @RequestMapping(value = "/api/message/receive", method = RequestMethod.POST)
  public Object receive(@RequestBody @Valid ReceivedMessage receivedMessage) {
    logger.info("POST, /api/message/receive, message received");
    ReceivedMessage received = new ReceivedMessage(receivedMessage.getMessage(), receivedMessage.getClient());
    UserMessage chatMessage = new UserMessage(received.getMessage().getId(),
        received.getMessage().getUserName(), received.getMessage().getMessage(),
        received.getMessage().getTimestamp());
    messageRepo.save(chatMessage);
    System.out.println(clientID);
    System.out.println(received.getClient().getId());
    if(!receivedMessage.getClient().getId().equals(clientID)) {
      //logger.info("POST, /api/message/receive, message received");
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.postForObject(url, received, ReceivedMessage.class);
    }
    return new Response("ok");
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
  public ErrorMessage MissingBodyParamter(MethodArgumentNotValidException e) {
    String temp = "Missing field(s): ";
    List<FieldError> errors = e.getBindingResult().getFieldErrors();
    for (FieldError error : errors) {
      temp = temp.concat(error.getField() + ", ");
    }
    return new ErrorMessage(temp);
  }
}


