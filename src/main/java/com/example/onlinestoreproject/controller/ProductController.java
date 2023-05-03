package com.example.onlinestoreproject.controller;

import com.example.onlinestoreproject.dto.ProductDTO;
import com.example.onlinestoreproject.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> findProduct(@RequestParam(defaultValue = "false", value = "sort") Boolean sort) {
        return productService.findProduct(sort);
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO product){
        return productService.create(product);
    }

    @PatchMapping
    public ResponseEntity<ProductDTO> update(@RequestParam Long id, @RequestBody ProductDTO product){
        return ResponseEntity.ok(productService.update(product,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<ProductDTO> search(@RequestParam(required = false) String name, @RequestParam(required = false) String description, @RequestParam(required = false) String categoryName){
        return productService.search(name, description, categoryName);
    }
}
