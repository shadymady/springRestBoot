package com.example.crudappboot.controller;

import com.example.crudappboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.printUsers());
        return "users/userindex";
    }

    @GetMapping("/{id}")
    public String printUserById(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("user", userService.printUserById(id));
        return "users/userpage";
    }

}
