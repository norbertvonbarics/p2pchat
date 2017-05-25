package com.greenfox.p2pchat.model;

import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceivedMessage {

  @Valid
  UserMessage message;

  @Valid
  Client client;

  public ReceivedMessage(UserMessage message, Client client) {
    this.message = message;
    this.client = client;
  }

  public ReceivedMessage() {
  }
}


