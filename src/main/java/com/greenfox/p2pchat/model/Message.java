package com.greenfox.p2pchat.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "userMessage")
@Getter
@Setter
public class Message {
  @Autowired
  RandomID random;


  @Id
  long id = random.randomID();
  String message;

  public Message(String message) {
    this.message = message;
  }

  public Message() {
  }
}
