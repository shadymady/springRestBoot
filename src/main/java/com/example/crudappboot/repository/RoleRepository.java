package com.example.crudappboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crudappboot.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role);
}