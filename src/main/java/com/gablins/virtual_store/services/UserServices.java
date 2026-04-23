package com.gablins.virtual_store.services;

import com.gablins.exceptions.UserNotFoundException;
import com.gablins.virtual_store.entities.User;
import com.gablins.virtual_store.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices
{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServices(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(String username, String password)
    {
        String senhaCriptografada = passwordEncoder.encode(password);
        User usuario = new User(username, senhaCriptografada);
        return userRepository.save(usuario);
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User create(User user)
    {
        return userRepository.save(user);
    }


    public User findByUsername(String username)
    {
        User user = new User();
        if (userRepository.findByUsername(username) != null) {
            user = (User) userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username + " não encontrado no sistema"));
        }
        return user;
    }


}
