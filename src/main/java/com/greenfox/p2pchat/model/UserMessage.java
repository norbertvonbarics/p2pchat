package com.greenfox.p2pchat.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "userMessage")
@Getter
@Setter
public class UserMessage {

  @Id
  long id;
  String userName;
  String text;
  Timestamp time;

  public UserMessage(String userName, String message) {
    this.id = (int) (1000000 + (Math.random() * 999999));
    this.userName = userName;
    this.text = message;
    this.time = new Timestamp(System.currentTimeMillis());
  }

  public UserMessage(long id, String userName, String text, Timestamp time) {
    this.id = id;
    this.userName = userName;
    this.text = text;
    this.time = time;
  }

  public UserMessage() {
  }
}
