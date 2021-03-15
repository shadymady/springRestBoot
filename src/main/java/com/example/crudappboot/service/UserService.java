package com.example.crudappboot.service;

import com.example.crudappboot.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    public void save(User user);

    public void delete(Long id);

    public void edit(User user);

    public User printUserById(Long id);

    public List<User> printUsers();

}