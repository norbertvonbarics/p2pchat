package com.greenfox.p2pchat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OkResponse {

  String status;

  public OkResponse(String status) {
    this.status = status;
  }

  public OkResponse() {
  }
}
