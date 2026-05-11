package com.gablins.VO;

import com.gablins.virtual_store.entities.CartItem;
import com.gablins.virtual_store.entities.Product;
import com.gablins.virtual_store.entities.User;
import jakarta.persistence.*;

public class CartItemDTO
{

    private Long id;
    private String productName;
    private double productPrice;
    private int productQuantity;



    public CartItemDTO(Long id, String productName, double productPrice, int productQuantity)
    {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public CartItemDTO()
    {
    }
    public Double subtotal()
    {
        return productPrice * productQuantity;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public double getProductPrice()
    {
        return productPrice;
    }

    public void setProductPrice(double productPrice)
    {
        this.productPrice = productPrice;
    }

    public int getProductQuantity()
    {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity)
    {
        this.productQuantity = productQuantity;
    }


    public static CartItemDTO convertToDTO(CartItem cartItem)
    {
        return new CartItemDTO(
                cartItem.getId(),cartItem.getProduct().getName(),cartItem.getProduct().getPrice(), cartItem.getQuantity()
        );

    }

}
