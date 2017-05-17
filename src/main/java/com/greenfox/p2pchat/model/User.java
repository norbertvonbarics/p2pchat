package com.greenfox.p2pchat.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chatUsers")
@Getter
@Setter
public class User {

  @Id
  long id;
  String name;

  public User(String userName, long id) {
  this.name = userName;
  }

  public User(long id, String name) {
    this.id = id;
    this.name = name;
  }
}
