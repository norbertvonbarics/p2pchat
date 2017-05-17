package com.greenfox.p2pchat.model;

public class RandomID {

  public int randomID(){
    return (int)(1000000 + Math.random() * 999999);
  }
}
