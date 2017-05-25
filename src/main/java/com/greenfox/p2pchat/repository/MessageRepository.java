package com.greenfox.p2pchat.repository;

import com.greenfox.p2pchat.model.UserMessage;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<UserMessage, Long> {
  public List<UserMessage> findFirst10ByOrderByTimestampDesc();
}
