package com.example.onlinestoreproject.repository;

import com.example.onlinestoreproject.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    @EntityGraph(attributePaths = {"categories"})
    List<Product> findFechAllBy(Sort sort);
    @EntityGraph(attributePaths = {"categories"})
    List<Product> findAll(Specification<Product> specif);



}
