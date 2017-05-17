package com.greenfox.p2pchat.service;

import com.greenfox.p2pchat.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
