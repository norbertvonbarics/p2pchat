package com.greenfox.p2pchat.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chat {

  @Id
  long id;
  String message;

  public Chat() {
  }

  public Chat(long id, String message) {
    this.id = id;
    this.message = message;
  }
}
