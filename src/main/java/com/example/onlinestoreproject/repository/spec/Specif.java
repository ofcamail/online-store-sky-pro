package com.example.onlinestoreproject.repository.spec;

import com.example.onlinestoreproject.model.Category;
import com.example.onlinestoreproject.model.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
public class Specif {
    public static Specification<Product> byName(String name){
        return (root, query, criteriaBuilder) -> {
            if (name==null){
                return criteriaBuilder.conjunction();
            }
            else {
                root.join("categories", JoinType.LEFT);
                return criteriaBuilder.like(root.get("name"), "%" + name + "%");
            }
        };
    }

    public static Specification<Product> byDescription(String desc){
        return (root, query, criteriaBuilder) -> {
            if (desc==null){
                return criteriaBuilder.conjunction();
            }
            else {
                root.join("categories", JoinType.LEFT);
                return criteriaBuilder.like(root.get("description"), "%" + desc + "%");
            }
        };
    }

    public static Specification<Product> byCategory(String category){
        return (root, query, criteriaBuilder) -> {
            if (category==null){
                return criteriaBuilder.conjunction();
            }
            Join<Product, Category> categoryJoin = root.join("categories", JoinType.LEFT);
            return criteriaBuilder.equal(categoryJoin.get("name"), category);
        };
    }
}
