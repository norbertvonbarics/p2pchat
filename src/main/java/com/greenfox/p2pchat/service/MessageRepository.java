package com.greenfox.p2pchat.service;

import com.greenfox.p2pchat.model.UserMessage;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<UserMessage, Long> {
}
