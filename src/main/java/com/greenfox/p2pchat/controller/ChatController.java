package com.greenfox.p2pchat.controller;

import com.greenfox.p2pchat.*;
import com.greenfox.p2pchat.model.RandomID;
import com.greenfox.p2pchat.model.User;
import com.greenfox.p2pchat.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

  @Autowired
  UserRepository userRepo;

  @RequestMapping("/")
  public String chat(){
    return "index";
  }

  @RequestMapping(value = "/enter", method = RequestMethod.GET)
  public String enter() {
    return "enter";
  }

  @RequestMapping(value = "/enter/add", method = RequestMethod.POST)
  public String addUser(@RequestParam(name= "userName") String userName) {
    RandomID random = new RandomID();
    userRepo.save(new User(userName, random.randomID()));
    return "redirect:/";
  }

  @ExceptionHandler(Exception.class)
  public ErrorMessage parameterMissing(Exception e) {
    System.err.println(e.getMessage());
    return new ErrorMessage("shit just hits the fan");
  }
}
