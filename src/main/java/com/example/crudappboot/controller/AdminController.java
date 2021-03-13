package com.example.crudappboot.controller;


import com.example.crudappboot.model.Role;
import com.example.crudappboot.model.User;
import com.example.crudappboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("users", userService.printUsers());
        return "admin/index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "userCheck", required = false) boolean userCheck,
                         @RequestParam(value = "adminCheck", required = false) boolean adminCheck) {
        Set<Role> roles = new HashSet<>();
        if (userCheck) {
            roles.add(new Role(2L, "ROLE_USER"));
        }
        if (adminCheck) {
            roles.add(new Role(1L, "ROLE_ADMIN"));
        }
        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userService.printUserById(id);
        model.addAttribute("users", user);
        model.addAttribute("roles", user.getRoles().toString());
        return "admin/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "userCheck", required = false) boolean userCheck,
                         @RequestParam(value = "adminCheck", required = false) boolean adminCheck) {
        user.setRoles(null);
        Set<Role> roles = new HashSet<>();
        if (adminCheck) {
            roles.add(new Role(1L, "ROLE_ADMIN"));
        }
        if (userCheck) {
            roles.add(new Role(2L, "ROLE_USER"));
        }
        user.setRoles(roles);
        userService.edit(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.printUserById(id));
        return "admin/adminpage";
    }
}