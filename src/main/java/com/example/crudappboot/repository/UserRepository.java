package com.example.crudappboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crudappboot.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
