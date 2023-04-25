package com.WarriorPoses.iigastudio.repositories;


import com.WarriorPoses.iigastudio.models.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    }