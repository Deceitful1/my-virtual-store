package com.gablins.virtual_store.services;

import com.gablins.configuration.JWTUserData;
import com.gablins.exceptions.UserNotFoundException;
import com.gablins.virtual_store.entities.User;
import com.gablins.virtual_store.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public JWTUserData registerUser(String username, String password)
    {
        String senhaCriptografada = passwordEncoder.encode(password);

        User usuario = new User(username, senhaCriptografada, new ArrayList<>());

        var result = userRepository.save(usuario);
        System.out.println(result.getId());
        return convertToDTO(result);
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

    public static JWTUserData convertToDTO(User user)
    {
        return new JWTUserData(user.getId(), user.getUsername());
    }

    public User convertToUser(JWTUserData user)
    {
        User usuario = userRepository.findById(user.userId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        return usuario;
    }
    public User findById(Long id)
    {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }


    public JWTUserData update(User user)
    {
        User user1 = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        user1.setCartItems(user1.getCartItems());
        var result = userRepository.save(user1);
        return convertToDTO(result);


    }


}
