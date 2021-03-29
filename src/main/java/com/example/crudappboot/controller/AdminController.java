package com.example.crudappboot.controller;

import com.example.crudappboot.model.User;

import com.example.crudappboot.model.UserDTO;
import com.example.crudappboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController{

    @Autowired
    private UserService userService;

    @GetMapping
    public String adminPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", user);
        return "admin/index";
    }

    @GetMapping("getCurrentUser")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        UserDTO currentUser = userService.getUserByName(principal.getName());
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

}