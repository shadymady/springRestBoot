package com.example.crudappboot.controller;

import com.example.crudappboot.model.User;
import com.example.crudappboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()
    public String printUsers(ModelMap model, @AuthenticationPrincipal User user) {
        model.addAttribute("users", userServiceImpl.printUsers());
        model.addAttribute("user", user);

        return "users/userindex";
    }

    @GetMapping("/{id}")
    public String printUserById(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("user", userServiceImpl.printUserById(id));
        return "users/userpage";
    }

}
