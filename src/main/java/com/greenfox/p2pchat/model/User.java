package com.greenfox.p2pchat.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

  @Id
  long id;
  String name;

  public User(String userName) {
  }

  public User(long id, String name) {
    this.id = id;
    this.name = name;
  }
}
