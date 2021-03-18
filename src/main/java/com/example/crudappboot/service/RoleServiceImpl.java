package com.example.crudappboot.service;

import com.example.crudappboot.model.Role;
import com.example.crudappboot.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void add(Role role) {
        roleRepository.save(role);
    }

    public void edit(Role role) {
        roleRepository.save(role);
    }

    public Role getById(Long id) {
        Role role = null;
        Optional<Role> opt = roleRepository.findById(id);
        if (opt.isPresent()) {
            role = opt.get();
        }
        return role;
    }

    public Role getByName(String name) {
        return roleRepository.findByName(name);
    }
}
