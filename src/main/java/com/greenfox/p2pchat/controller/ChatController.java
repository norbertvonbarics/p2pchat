package com.greenfox.p2pchat.controller;

import com.greenfox.p2pchat.*;
import com.greenfox.p2pchat.model.Log;
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
      return "redirect:/enter";
    } else {
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
      User user = userRepo.findOne((long)1);
      updateExecute(user, newName);
      error = "";

      return "redirect:/";
    }
  }

  @RequestMapping(value= "/update/execute", method= RequestMethod.PUT)
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
