package com.example.crudappboot.service;

import com.example.crudappboot.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    List<Role> getAllRoles();

    void add(Role role);

    void edit(Role role);

    Role getById(Long id);

    Role getByName(String name);
}