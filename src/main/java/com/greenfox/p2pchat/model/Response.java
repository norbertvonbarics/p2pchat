package com.greenfox.p2pchat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

  String status;

  public Response(String status) {
    this.status = status;
  }

  public Response() {
  }
}
