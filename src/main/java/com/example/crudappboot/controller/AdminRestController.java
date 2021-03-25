package com.example.crudappboot.controller;

import com.example.crudappboot.model.UserDTO;
import com.example.crudappboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/admin")
public class AdminRestController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userServiceImpl.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<Set<String>> getAllRoles() {
        return new ResponseEntity<>(userServiceImpl.getNameRoles(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {
        userServiceImpl.addUser(user);
        return new ResponseEntity<>(userServiceImpl.getUserByName(user.getFirstName()), HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<UserDTO> editUser(@RequestBody UserDTO user) {
        userServiceImpl.editUser(user);
        return new ResponseEntity<>(userServiceImpl.getUserByName(user.getFirstName()), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getCurrentUser")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        UserDTO currentUser = userServiceImpl.getUserByName(principal.getName());
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}