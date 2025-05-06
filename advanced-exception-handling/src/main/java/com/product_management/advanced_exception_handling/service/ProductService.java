package com.product_management.advanced_exception_handling.service;

import com.product_management.advanced_exception_handling.exception.InvalidProductDataException;
import com.product_management.advanced_exception_handling.exception.ProductNotFoundException;
import com.product_management.advanced_exception_handling.model.Product;
import org.springframework.stereotype.Service;

import java.security.PrivilegedActionException;

@Service
public class ProductService {

    public Product getProductById(Long id){
        if(id<=0){
            throw new ProductNotFoundException("Product not found withing the id: " +id);
        }
        Product product = new Product();
        product.setName("Sample Product " +id);
        product.setPrice(100.5 * id);
        return product;
    }

    public Product createProduct(Product product){
        if(product.getPrice()>100000){
            throw new InvalidProductDataException("Product price can not be greater than 10,000");
        }
        return product;
    }
}
