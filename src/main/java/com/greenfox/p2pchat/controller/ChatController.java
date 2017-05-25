package com.greenfox.p2pchat.controller;

import com.greenfox.p2pchat.*;
import com.greenfox.p2pchat.model.Client;
import com.greenfox.p2pchat.model.Log;
import com.greenfox.p2pchat.model.ReceivedMessage;
import com.greenfox.p2pchat.model.User;
import com.greenfox.p2pchat.model.UserMessage;
import com.greenfox.p2pchat.repository.MessageRepository;
import com.greenfox.p2pchat.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class ChatController {

  Logger logger = Logger.getLogger(P2pchatApplication.class.getName());

  private String uri = System.getenv("CHAT_APP_PEER_ADDRESSS") + "/api/message/receive";
  private String clientID = System.getenv("CHAT_APP_UNIQUE_ID");
  String error = "";

  @Autowired
  UserRepository userRepo;

  @Autowired
  MessageRepository messageRepo;

  @RequestMapping("/")
  public String chat(Model model) {
    model.addAttribute("messageRepo", messageRepo.findAllByOrderByTimestampDesc());
    return "index";
  }

  @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
  public String addMessage(@RequestParam(name = "message") String message) {
    if (userRepo.count() == 0) {
      logger.error("POST, /addMessage, no username added");
      return "redirect:/enter";
    } else if (message.length() == 0){
      logger.error("POST, /addMessage, message length = 0");
      return "redirect:/";
    } else {
      logger.info("POST, /addMessage, message saved");
      UserMessage chatMessage = new UserMessage(userRepo.findOne((long) 1).getName(), message);
      messageRepo.save(chatMessage);
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.postForObject(uri, new ReceivedMessage(chatMessage, new Client(clientID)), ReceivedMessage.class);
      return "redirect:/";
    }
  }

  @RequestMapping(value = "/enter", method = RequestMethod.GET)
  public String enter(Model model) {
    logger.info("GET, /enter, enter template loaded");
    model.addAttribute("error", error);
    return "enter";
  }

  @RequestMapping(value = "/enter/add", method = RequestMethod.POST)
  public String addUser(@RequestParam(name = "userName") String userName) {
    if (userName.isEmpty()) {
      logger.error("POST, /enter/add, no username provided");
      error = "The username field is empty";
      return "redirect:/enter";

    } else if (userRepo.count() == 0) {
      logger.info("POST, /enter/add, " + userName + " registered");
      userRepo.save(new User(userName));
      error = "";
      return "redirect:/";

    } else {
      logger.error("POST, /addMessage, something went wrong");
      return "redirect:/update";
    }
  }

  @RequestMapping("/update")
  public String update(Model model, @RequestParam("newName") String newName) {
    if (newName.isEmpty()) {
      logger.error("PUT, /update, no username provided");
      error = "The username field is empty.";
      return "redirect:/update";
    } else {
      logger.info("PUT, /update, username provided");
      User user = userRepo.findOne((long) 1);
      updateExecute(user, newName);
      error = "";
      return "redirect:/";
    }
  }

  @RequestMapping(value = "/update/execute", method = RequestMethod.PUT)
  public void updateExecute(User user, String newName) {
    logger.info("PUT, /update, username updated");
    user.setName(newName);
    userRepo.save(user);
  }

  @ExceptionHandler(Exception.class)
  public ErrorMessage parameterMissing(Exception e) {
    System.err.println(e.getMessage());
    return new ErrorMessage("shit just hits the fan");
  }
}

/*
Testride:
1681395 John Doe Hi, is your name Google?
1399627 Jane Doe (No, Why?)
1927907 John Doe Because you have everything I'm looking for!
1454418 Jane Doe ಠ╭╮ಠ
1296433 John Doe ¯\_(ツ)_/¯
 */
