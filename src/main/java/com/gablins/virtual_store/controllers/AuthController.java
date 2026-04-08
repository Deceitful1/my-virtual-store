package com.gablins.virtual_store.controllers;

import com.gablins.configuration.TokenConfig;
import com.gablins.virtual_store.entities.User;
import com.gablins.virtual_store.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController
{
    @Autowired
    private UserServices user;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final TokenConfig tokenConfig;

    public AuthController(PasswordEncoder passwordEncoder, TokenConfig tokenConfig, AuthenticationManager authenticationManager, UserServices user)
    {
        this.user = user;
        this.passwordEncoder = passwordEncoder;
        this.tokenConfig = tokenConfig;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request)
    {
        User usuario = new User();
        usuario.setUsername(request.get("username"));
        usuario.setPassword(passwordEncoder.encode(request.get("password")));
        user.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request)
    {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.get("username"), request.get("password"));
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);
        return ResponseEntity.status(200).body(token);

    }
}



