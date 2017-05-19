package com.greenfox.p2pchat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
  String id;

  public Client(String id){
    this.id = id;
  }

  public Client() {
  }
}
