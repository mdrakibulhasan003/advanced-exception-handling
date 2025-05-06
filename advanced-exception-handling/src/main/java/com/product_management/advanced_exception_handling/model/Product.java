package com.product_management.advanced_exception_handling.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Product {

    @NotBlank(message = "Product name can not be emty")
    @Size(min = 3, max = 50, message = "Product name should be within 50 characters")
    private String name;

    @Min(value = 1, message = "Product price must be greater than 1")
    private Double price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
