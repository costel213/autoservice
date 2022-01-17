package com.costelmitrea.autoservice.repositories;

import com.costelmitrea.autoservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
