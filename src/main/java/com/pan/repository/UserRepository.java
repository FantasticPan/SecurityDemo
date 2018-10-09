package com.pan.repository;

import com.pan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by FantasticPan on 2018/6/4.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
