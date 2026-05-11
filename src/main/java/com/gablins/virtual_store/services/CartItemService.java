package com.gablins.virtual_store.services;

import com.gablins.VO.CartItemDTO;
import com.gablins.exceptions.CartItemNotFoundException;
import com.gablins.exceptions.ProductNotFoundException;
import com.gablins.exceptions.UserNotFoundException;
import com.gablins.virtual_store.entities.CartItem;
import com.gablins.virtual_store.entities.Product;
import com.gablins.virtual_store.entities.User;
import com.gablins.virtual_store.repositories.CartItemRepository;
import com.gablins.virtual_store.repositories.ProductRepository;
import com.gablins.virtual_store.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService
{
    @Autowired
    private CartItemRepository repository;

    private final UserRepository userRepository;

    private final UserServices userServices;
    private final ProductRepository productRepository;

    public CartItemService(UserRepository userRepository, UserServices userServices, ProductRepository productRepository)
    {
        this.userRepository = userRepository;
        this.userServices = userServices;
        this.productRepository = productRepository;
    }

    public List<CartItemDTO> findAll(Long userId)
    {
        var findAll = repository.findByUserId(userId);
        var data = findAll.stream().map(l -> CartItemDTO.convertToDTO(l)).toList();
        return data;
    }

    public CartItemDTO findById(long userId, Long productId)
    {
        var entity = repository.findByProductIdAndUserId(productId, userId).map(CartItemDTO::convertToDTO).orElseThrow(() -> new CartItemNotFoundException("Cart Item Not Found"));
        return entity;
    }

    @Transactional
    public CartItemDTO addOrUpdate(CartItem cartItem)
    {
        User user = userRepository.findById(cartItem.getUser().getId()).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        System.out.println("Buscando usuário com id:" + user.getId());

        Product product = productRepository.findById(cartItem.getProduct().getId()).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
        System.out.println("Buscando produto com ID: " + product.getId());
        var entity = repository.findByUserId(user.getId());
        Optional<CartItem> result = entity.stream().filter(c -> c.getProduct().getId() == cartItem.getProduct().getId()).findFirst();

        if (result.isPresent()) {
            CartItem c = result.get();
            c.setQuantity(c.getQuantity() + cartItem.getQuantity());
            var saved = repository.save(c);
            return CartItemDTO.convertToDTO(saved);

        } else {
            cartItem.setId(null);

            System.out.println("encontrado");
            CartItem item = new CartItem();
            item.setUser(user);
            item.setProduct(product);
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(product.getPrice());

            user.getCartItems().add(item);
            var saved = repository.saveAndFlush(item);

            System.out.println("saved:" + saved.getId());




            return CartItemDTO.convertToDTO(saved);
        }


    }

    public CartItemDTO update(Long productId, User user, int quantity)
    {
        CartItem cartItem = repository.findByProductIdAndUserId(productId, user.getId()).orElseThrow(() -> new CartItemNotFoundException("Product with id:" + productId + " not found"));
        cartItem.setQuantity(quantity);
        var result = repository.save(cartItem);
        var data = CartItemDTO.convertToDTO(result);

        return data;

    }


    public void delete(Long productId, Long userId)
    {
        Optional<CartItem> item = repository.findByProductIdAndUserId(productId, userId);
        if (item.isPresent()) {
            repository.delete(item.get());
        } else {
            throw new CartItemNotFoundException("Product with id:" + productId + " not found");
        }

    }


}
