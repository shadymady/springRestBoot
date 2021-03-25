package com.example.crudappboot.service;

import com.example.crudappboot.model.User;
import com.example.crudappboot.model.UserDTO;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO getUserByName(String name);

    void addUser(UserDTO user);

    void deleteUser(Long id);

    void editUser(UserDTO user);

    Set<String> getNameRoles();
}