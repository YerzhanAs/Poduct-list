package com.geekbrains.demoboot.services;

import com.geekbrains.demoboot.entities.Item;
import com.geekbrains.demoboot.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ItemsService {
    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Iterable<Item> getAllItems(){
//        System.out.println(itemRepository.findByTitle("Tree"));
//        System.out.println(itemRepository.findByCostBetween(30,70));
        return itemRepository.findAll();
    }

      public Page getItemsWithPagingAndFiltering(Specification<Item> specifications, Pageable pageable){
//        List<ItemProjection> itemProjections=itemRepository.getProjections();
//        itemProjections.stream().forEach(O-> System.out.println(O.title()+" "+O.cost()));
        return itemRepository.findAll(specifications, pageable);
      }
}
