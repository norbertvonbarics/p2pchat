package com.greenfox.p2pchat.controller;

import com.greenfox.p2pchat.ErrorMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

  @RequestMapping("/")
  public String chat(){
    System.out.println("MUUUUKODJ");
    return "index";
  }

  @ExceptionHandler(Exception.class)
  public ErrorMessage parameterMissing(Exception e) {
    System.err.println(e.getMessage());
    return new ErrorMessage("shit just hits the fan");
  }
}
