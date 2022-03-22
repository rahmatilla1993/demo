package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.models.Result;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

//    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    @GetMapping
    public HttpEntity<?> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

//    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getProductById(@PathVariable Integer id) {
        Result result = productService.getProductById(id);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(result);
    }

//    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody Product product) {
        Result result = productService.addProduct(product);
        return ResponseEntity.ok(result);
    }

//    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProductById(@PathVariable Integer id) {
        Boolean isDone = productService.deleteProductById(id);
        return ResponseEntity.status(isDone ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body("Changes accepted");
    }
}
