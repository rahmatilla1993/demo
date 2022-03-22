package com.example.demo.service;

import com.example.demo.models.Product;
import com.example.demo.models.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    private List<Product> products = Stream.of(
            new Product(1, "Iphone Pro Max"),
            new Product(2, "Samsung Galaxy S22"),
            new Product(3, "MI 6")).collect(Collectors.toList());

    public List<Product> getAllProducts() {
        return products;
    }

    public Result getProductById(Integer id) {
        Product productById = products.stream().filter(
                product -> product.getId().equals(id)).findFirst().orElse(null);
        if (productById == null) {
            return new Result("Product not found", false);
        }
        return new Result(true, productById);
    }

    public Result addProduct(Product product) {
        products.add(product);
        return new Result("Product added", true, product);
    }

    public Boolean deleteProductById(Integer id) {
        return products.removeIf(product -> product.getId().equals(id));
    }
}
