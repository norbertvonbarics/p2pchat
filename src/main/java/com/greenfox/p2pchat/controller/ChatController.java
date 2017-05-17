package com.greenfox.p2pchat.controller;

import com.greenfox.p2pchat.*;
import com.greenfox.p2pchat.model.RandomID;
import com.greenfox.p2pchat.model.User;
import com.greenfox.p2pchat.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

  String error = "";

  @Autowired
  UserRepository userRepo;

  @RequestMapping("/")
  public String chat() {
    System.out.println(System.getenv("CHAT_APP_LOGLEVEL"));
    return "index";
  }

  @RequestMapping(value = "/enter", method = RequestMethod.GET)
  public String enter(Model model) {
    model.addAttribute("error", error);
    return "enter";
  }

  @RequestMapping(value = "/enter/add", method = RequestMethod.POST)
  public String addUser(@RequestParam(name = "userName") String userName, Model model) {
    if (userName.isEmpty()) {
      error = "The username field is empty";
    } else {
      userRepo.save(new User(userName));
    }
    return "redirect:/enter";
  }

  @ExceptionHandler(Exception.class)
  public ErrorMessage parameterMissing(Exception e) {
    System.err.println(e.getMessage());
    return new ErrorMessage("shit just hits the fan");
  }
}
