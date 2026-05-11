package com.gablins.virtual_store.controllers;

import com.gablins.VO.CartItemDTO;
import com.gablins.configuration.JWTUserData;
import com.gablins.virtual_store.entities.CartItem;
import com.gablins.virtual_store.entities.User;
import com.gablins.virtual_store.services.CartItemService;
import com.gablins.virtual_store.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartItemController
{
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private UserServices userServices;

    @GetMapping()
    public ResponseEntity<List<CartItemDTO>> getCartItems(@AuthenticationPrincipal final JWTUserData user)
    {
        System.out.println(user);
        return ResponseEntity.status(200).body(cartItemService.findAll(Long.valueOf(user.userId())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDTO> getCartItem(@PathVariable final Long id, @AuthenticationPrincipal final JWTUserData user)
    {
        return ResponseEntity.status(200).body(cartItemService.findById(user.userId(), id));
    }

    @PostMapping
    public ResponseEntity<CartItemDTO> addToCart(@AuthenticationPrincipal final JWTUserData user, @RequestBody CartItem cartItem)
    {
        User user1 = userServices.findById(user.userId());
        cartItem.setUser(user1);
        return ResponseEntity.status(201).body(cartItemService.addOrUpdate(cartItem));
    }




}
