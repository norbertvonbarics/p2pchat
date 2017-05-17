package com.greenfox.p2pchat.service;

import com.greenfox.p2pchat.model.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Long> {

}
