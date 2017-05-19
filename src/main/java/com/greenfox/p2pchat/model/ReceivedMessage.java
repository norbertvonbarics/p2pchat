package com.greenfox.p2pchat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceivedMessage {

  UserMessage message;
  Client client;

  public ReceivedMessage(UserMessage message, Client client) {
    this.message = message;
    this.client = client;
  }

  public ReceivedMessage() {
  }
}


