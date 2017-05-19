package com.greenfox.p2pchat.model;

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
  long id = (int)(1000000 + (Math.random() * 999999));
  String userName;
  String message;

  public UserMessage(String userName, String message) {
    this.userName = userName;
    this.message = message;
  }

  public UserMessage() {
  }
}
