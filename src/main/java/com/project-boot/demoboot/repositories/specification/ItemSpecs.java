package com.geekbrains.demoboot.repositories.specification;

import com.geekbrains.demoboot.entities.Item;
import org.springframework.data.jpa.domain.Specification;



public class ItemSpecs {

    public static Specification<Item> titleContains(String word){
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder ) ->{
            return  criteriaBuilder.like(root.get("title"), "%"+word+"%");
        };
    }

   public static Specification<Item> costGreaterThanEq(int value){
       return (Specification<Item>) (root, criteriaQuery, criteriaBuilder ) ->{
           return  criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), value);
       };
   }

}
