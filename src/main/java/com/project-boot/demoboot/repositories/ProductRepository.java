package com.geekbrains.demoboot.repositories;

import com.geekbrains.demoboot.entities.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product>  {

    @Query("SELECT p FROM Product p ORDER BY views DESC")
     List<Product> getTop3Products();
}
