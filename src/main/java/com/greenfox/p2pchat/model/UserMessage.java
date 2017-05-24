package com.greenfox.p2pchat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "userMessage")
@Getter
@Setter
public class UserMessage {

  @Id
  @NotNull
  @JsonProperty(value = "id")
  long id;

  @NotNull
  @JsonProperty(value = "text")
  String message;

  @NotNull
  @JsonProperty(value = "username")
  String userName;

  @NotNull
  @JsonProperty(value = "timestamp")
  Timestamp timestamp;

  public UserMessage(String userName, String message) {
    this.id = (long) (1000000 + (Math.random() * 9999999));
    this.userName = userName;
    this.message = message;
    this.timestamp = new Timestamp(System.currentTimeMillis());
  }

  public UserMessage(long id, String userName, String message, Timestamp timestamp) {
    this.id = id;
    this.userName = userName;
    this.message = message;
    this.timestamp = timestamp;
  }

  public UserMessage() {
  }
}
