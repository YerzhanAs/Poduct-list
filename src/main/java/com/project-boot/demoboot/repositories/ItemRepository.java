package com.geekbrains.demoboot.repositories;

import com.geekbrains.demoboot.entities.Item;
import com.geekbrains.demoboot.entities.ItemProjection;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item,Long>, JpaSpecificationExecutor<Item> {
   // SELECT i FROM Item i WHERE i.title=1?
//    Item findByTitle(String title);
//    List<Item> findByCostBetween(int min, int max);
//    List<Item> findByCostOrderByTitleDesc(int cost);

//    @Query(value="",nativeQuery = true)
//    List<Item> myMethodName();

//    @Query(value="SELECT i FROM Item i WHERE i.title LIKE %?1")
//    List<Item> findByTitleEndWith(String substr);
//
    List<ItemProjection> findItemsProjectionsByCost(int cost);
}
