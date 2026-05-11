package com.gablins.virtual_store.controllers;

import com.gablins.configuration.JWTUserData;
import com.gablins.virtual_store.entities.User;
import com.gablins.virtual_store.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController
{
    @Autowired
    private UserServices userServices;

    @GetMapping
    public ResponseEntity<User> getUser(@AuthenticationPrincipal JWTUserData user)
    {
        User user1 = userServices.findById(user.userId());
        return ResponseEntity.status(200).body(user1);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id)
    {
        return ResponseEntity.status(200).body(userServices.findById(id));
    }


}
