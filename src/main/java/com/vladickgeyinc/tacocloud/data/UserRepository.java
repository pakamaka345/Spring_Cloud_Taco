package com.vladickgeyinc.tacocloud.data;

import com.vladickgeyinc.tacocloud.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
