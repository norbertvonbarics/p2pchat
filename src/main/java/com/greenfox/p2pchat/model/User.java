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

  public User(String userName, long id) {
  this.name = userName;
  }

  public User(String name) {
    this.id = id;
    this.name = name;
  }
}
