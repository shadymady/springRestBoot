package com.example.crudappboot.controller;

import com.example.crudappboot.model.UserDTO;
import com.example.crudappboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String user(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO user = userService.getUserByName(authentication.getName());
        model.addAttribute("user", user);
        return "/users/userindex";
    }

}
