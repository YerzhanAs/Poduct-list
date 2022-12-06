package com.geekbrains.demoboot.repositories.specification;

import com.geekbrains.demoboot.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpec {
    public static Specification<Product> titleContains(String word){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder ) ->{
            return  criteriaBuilder.like(root.get("title"), "%"+word+"%");
        };
    }

    public static Specification<Product> priceGreaterThanEq(BigDecimal value){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder ) ->{
            return  criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
        };
    }

    public static Specification<Product> priceLesserThanEq(BigDecimal value){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder ) ->{
            return  criteriaBuilder.lessThanOrEqualTo(root.get("price"), value);
        };
    }
}
