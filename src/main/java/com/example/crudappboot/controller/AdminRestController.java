package com.example.crudappboot.controller;

import com.example.crudappboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/admin")
public class AdminRestController {

    @Autowired
    private UserServiceImpl userServiceImpl;
}
