package com.gablins.virtual_store.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gablins.VO.ProductVO;
import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Table(name = "product")
@Entity
@JsonPropertyOrder({"id","name","description","price","quantity"})
public class Product
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 100, name = "name")
    private String name;
    @Column(nullable = false, name = "description")
    private String description;
    @Column(nullable = false, name = "price")
    private double price;
    @Column(nullable = false, name = "quantity")
    private int quantity;

    public Product(String name, String description, double price, int quantity)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Product()
    {
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public static Product convertToProduct(ProductVO productVO)
    {
        return new Product(productVO.getName(), productVO.getDescription(), productVO.getPrice(), productVO.getQuantity());
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return getId() == product.getId();
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

}
