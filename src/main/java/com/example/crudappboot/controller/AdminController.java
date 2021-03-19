package com.example.crudappboot.controller;

import com.example.crudappboot.model.Role;
import com.example.crudappboot.model.User;

import com.example.crudappboot.service.RoleServiceImpl;
import com.example.crudappboot.service.UserServiceImpl;
import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController{

    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping()
    public String allUsers(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("roles", roleServiceImpl.getAllRoles());
        model.addAttribute("users", userServiceImpl.printUsers());
        model.addAttribute("user", user);
        return "admin/index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "roless") String[] role) throws NotFoundException {
        Set<Role> roles = new HashSet<>();
        for (String roleStr : role) {
            roles.add(roleServiceImpl.getByRole(roleStr));
        }
        user.setRoles(roles);
        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userServiceImpl.printUserById(id);
        model.addAttribute("users", user);
        model.addAttribute("roles", user.getRoles().toString());
        return "admin/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute User user,
                         @RequestParam(value = "roless") String[] role){

        Set<Role> roles = new HashSet<>();
        for (String roleStr : role) {
            roles.add(roleServiceImpl.getByRole(roleStr));
        }
        user.setRoles(roles);
        userServiceImpl.edit(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.delete(id);
        return "redirect:/admin";
    }

}