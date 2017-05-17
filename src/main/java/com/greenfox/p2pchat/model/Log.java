package com.greenfox.p2pchat.model;


import java.util.Date;

public class Log {

  private Date date;
  private String level;

  public Log(Date date, String level) {
    this.date = new Date();
    this.level = level;
  }

  public Log() {
  }
}
