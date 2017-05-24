package com.greenfox.p2pchat.model;

import com.greenfox.p2pchat.P2pchatApplication;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import org.apache.log4j.Logger;

public class Log {

  String path;
  String method;
  LocalDateTime time;
  String logLevel;
  String data;

  public Log(String logLevel, String method, String path, String data) {
    this.time = LocalDateTime.now();
    this.logLevel = logLevel;
    this.method = method;
    this.data = data;
    this.path = path;
  }

  public String printLog(Log log) {
    StringBuilder str = new StringBuilder();
    str.append(log.time);
    str.append(" ");
    str.append(log.logLevel);
    str.append(" Request ");
    str.append(log.method);
    str.append(" ");
    str.append(log.path);
    str.append(" text=");
    str.append(log.data);
    return str.toString();
  }
}


