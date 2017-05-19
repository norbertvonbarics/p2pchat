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
  String username;
  String text;
  Timestamp timestamp;

  public UserMessage(String username, String message) {
    this.id = (int) (1000000 + (Math.random() * 999999));
    this.username = username;
    this.text = message;
    this.timestamp = new Timestamp(System.currentTimeMillis());
  }

  public UserMessage(long id, String username, String text, Timestamp timestamp) {
    this.id = id;
    this.username = username;
    this.text = text;
    this.timestamp = timestamp;
  }

  public UserMessage() {
  }
}
