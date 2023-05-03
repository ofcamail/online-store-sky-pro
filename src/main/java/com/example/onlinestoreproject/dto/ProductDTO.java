package com.example.onlinestoreproject.dto;

import com.example.onlinestoreproject.model.Product;
import lombok.Data;

import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Instant creationDate;
    private Instant modificationDate;
    private Collection<CategoryDTO> categories;
    public static ProductDTO of(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setCreationDate(product.getCreationDate());
        dto.setModificationDate(product.getModificationDate());
        dto.setCategories(product.getCategories().stream().map(CategoryDTO::of).collect(Collectors.toList()));
        return dto;
    }
}
