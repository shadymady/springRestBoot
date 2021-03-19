package com.example.crudappboot.service;

import com.example.crudappboot.model.Role;
import com.example.crudappboot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);

    void delete(Long id);

    void edit(User user);

    User printUserById(Long id);

    List<User> printUsers();

}