package com.example.crudappboot.service;

import com.example.crudappboot.model.Role;
import com.example.crudappboot.model.User;
import com.example.crudappboot.model.UserDTO;
import com.example.crudappboot.repository.RoleRepository;
import com.example.crudappboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("ALL")
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(getDtoFromUser(user));
        }
        return users;
    }

    public UserDTO getUserById(Long id) {
        return getDtoFromUser(userRepository.findById(id).get());
    }

    public UserDTO getUserByName(String email) {
        return getDtoFromUser(userRepository.findUserByEmail(email));
    }

    public void addUser(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(getUserFromDto(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void editUser(UserDTO user) {
        User editUser = userRepository.findById(user.getId()).get();
        if (!user.getPassword().isEmpty()) {
            editUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        editUser.setRoles(getSetOfRoles(user.getRoles()));
        editUser.setFirstName(user.getFirstName());
        editUser.setLastName(user.getLastName());
        editUser.setEmail(user.getEmail());
        userRepository.save(editUser);
    }

    public Set<String> getNameRoles() {
        Set<String> nameRoles = new HashSet<>();
        for (Role role : roleRepository.findAll()) {
            nameRoles.add(role.getRole());
        }
        return nameRoles;
    }

    private User getUserFromDto(UserDTO userDto) {
        return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getAge(),
                userDto.getEmail(), userDto.getPassword(), getSetOfRoles(userDto.getRoles()));
    }

    private UserDTO getDtoFromUser(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getAge(),
                user.getEmail(), user.getPassword(), getSetOfString(user.getRoles()));
    }

    private Set<Role> getSetOfRoles(Set<String> nameRoles) {
        Set<Role> roles = new HashSet<>();
        for (String roleName : nameRoles) {
            roles.add(roleRepository.findByRole(roleName));
        }
        return roles;
    }

    public Set<String> getSetOfString(Set<Role> roles) {
        Set<String> nameRoles = new HashSet<>();
        for (Role role : roles) {
            nameRoles.add(role.getRole());
        }
        return nameRoles;
    }

}