package com.greenfox.p2pchat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  String name;

  public User(String username, long id) {
    this.name = username;
  }

  public User(String name) {
    this.name = name;
  }

  public User() {
  }
}
