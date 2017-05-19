package com.greenfox.p2pchat.controller;

import com.greenfox.p2pchat.*;
import com.greenfox.p2pchat.model.Log;
import com.greenfox.p2pchat.model.User;
import com.greenfox.p2pchat.model.UserMessage;
import com.greenfox.p2pchat.service.MessageRepository;
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

  @Autowired
  MessageRepository messageRepo;

  @RequestMapping("/")
  public String chat(Model model) {
    model.addAttribute("messageRepo", messageRepo.findAll());
    return "index";
  }

  @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
  public String addMessage(@RequestParam(name = "message") String message) {
    if (userRepo.findOne((long) 1).getName() == null) {
      return "redirect:/enter";
    } else {
      messageRepo.save(new UserMessage(userRepo.findOne((long) 1).getName(), message));
      return "redirect:/";
    }
  }

  @RequestMapping(value = "/enter", method = RequestMethod.GET)
  public String enter(Model model) {
    model.addAttribute("error", error);
    return "enter";
  }

  @RequestMapping(value = "/enter/add", method = RequestMethod.POST)
  public String addUser(@RequestParam(name = "userName") String userName) {
    if (userName.isEmpty()) {
      Log log = new Log("Error", "POST", "/enter/add", "no username provided");
      System.err.println(log.printLog(log));
      error = "The username field is empty";
      return "redirect:/enter";
    } else {
      Log log = new Log("INFO", "POST", "/enter/add", userName + " registered");
      System.out.println(log.printLog(log));
      userRepo.save(new User(userName));
      error = "";
      return "redirect:/";
    }
  }

  @RequestMapping("/update")
  public String update(Model model, @RequestParam("newName") String newName) {
    if (newName.isEmpty()) {
      Log log = new Log("Error", "PUT", "/update", "no username provided");
      System.err.println(log.printLog(log));
      error = "The username field is empty.";
      return "redirect:/update";
    } else {
      Log log = new Log("INFO", "PUT", "/update", newName);
      System.out.println(log.printLog(log));
      User user = userRepo.findOne((long) 1);
      updateExecute(user, newName);
      error = "";
      return "redirect:/";
    }
  }

  @RequestMapping(value = "/update/execute", method = RequestMethod.PUT)
  public void updateExecute(User user, String newName) {
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
1681395 John Doe Hi, is your name Google?
1399627 Jane Doe (No, Why?)
1927907 John Doe Because you have everything I'm looking for!
1454418 Jane Doe ಠ╭╮ಠ
1296433 John Doe ¯\_(ツ)_/¯
 */
