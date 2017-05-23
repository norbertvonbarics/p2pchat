package com.greenfox.p2pchat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "userMessage")
@Getter
@Setter
public class UserMessage {

  @Id
  @JsonProperty(value = "id")
  long id;
  @JsonProperty(value = "text")
  String message;
  @JsonProperty(value = "username")
  String userName;
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
