package com.example.crudappboot.service;

import com.example.crudappboot.model.User;
import com.example.crudappboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void edit(User user) {
        entityManager.merge(user);
    }

    public User printUserById(Long id) {
        return userRepository.getOne(id);
    }

    public List<User> printUsers(){
        return userRepository.findAll();
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email);
    }

}