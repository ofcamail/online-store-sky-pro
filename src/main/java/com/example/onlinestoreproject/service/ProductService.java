package com.example.onlinestoreproject.service;

import com.example.onlinestoreproject.dto.CategoryDTO;
import com.example.onlinestoreproject.dto.ProductDTO;
import com.example.onlinestoreproject.exception.NotFoundCategory;
import com.example.onlinestoreproject.exception.NotFoundProduct;
import com.example.onlinestoreproject.model.Category;
import com.example.onlinestoreproject.model.Product;
import com.example.onlinestoreproject.repository.CategoryRepository;
import com.example.onlinestoreproject.repository.ProductRepository;
import com.example.onlinestoreproject.repository.spec.Specif;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Transactional(readOnly = true)
    public List<ProductDTO> findProduct(Boolean sort) {
       List<Product> products = productRepository.findFechAllBy(
                sort ?
                        Sort.by(Sort.Direction.DESC, "createdDate") :
                        Sort.unsorted());
        return products.stream().map(ProductDTO::of).collect(Collectors.toList());
    }

    public ProductDTO create(ProductDTO product) {
        Product productEntity = new Product();
        return createOrUpdate(productEntity,product);
    }

    public ProductDTO update(ProductDTO productDTO, Long id) {
        Product product = productRepository.findById(id).orElseThrow(NotFoundProduct::new);
        return createOrUpdate(product,productDTO);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> search(String name, String description, String categoryName) {
        List<Product> products = productRepository.findAll(
                Specif.byName(name).or(Specif.byDescription(description)).or(Specif.byCategory(categoryName)));

        return products.stream().map(ProductDTO::of).collect(Collectors.toList());
    }
    private ProductDTO createOrUpdate(Product productEntity, ProductDTO product){
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        List<Long> categoriesId = product.getCategories().stream()
                .map(CategoryDTO::getId).collect(Collectors.toList());

        Collection<Category> categories = categoryRepository.findAllById(categoriesId);
        if (categories.size()!=categoriesId.size()) throw new NotFoundCategory();
        productEntity.setCategories(categories);

        productEntity = productRepository.save(productEntity);

        return ProductDTO.of(productEntity);

    }
}
