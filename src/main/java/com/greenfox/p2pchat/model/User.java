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
  long id = 1;
  String name;

  public User(String userName) {
    this.name = userName;
  }

  public User() {
  }
}
