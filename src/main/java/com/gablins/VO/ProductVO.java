package com.gablins.VO;

import com.gablins.virtual_store.entities.Product;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductVO
{
    private long id;
    private String name;
    private String description;
    private double price;
    private int quantity;

    public ProductVO(Long id,String name, String description, double price, int quantity)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
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

    public static ProductVO fromEntity(Product product)
    {
        return new ProductVO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
    }
    public static List<ProductVO> fromEntities(List<Product> products)
    {
        List<ProductVO> productVOS = new ArrayList<>();
        for(Product product : products)
        {
            productVOS.add(ProductVO.fromEntity(product));
        }
        return productVOS;
    }





    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ProductVO productVO)) return false;
        return getId() == productVO.getId();
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
