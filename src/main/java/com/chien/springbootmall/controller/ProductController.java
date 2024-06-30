package com.chien.springbootmall.controller;

import com.chien.springbootmall.dto.ProductRequest;
import com.chien.springbootmall.model.Product;
import com.chien.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);

        if(product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        System.out.println("postmaping product start");

        Integer productId = productService.createProduct(productRequest);
        System.out.println("新增的ID : " + productId);
        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product>updateProduct(@PathVariable Integer productId,
                                                @RequestBody @Valid ProductRequest productRequest) {
      //檢查product是否存在
       Product product = productService.getProductById(productId);
       if(product == null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       //修改商品的數據
        productService.updateProduct(productId,productRequest);

        Product updateProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }


}
