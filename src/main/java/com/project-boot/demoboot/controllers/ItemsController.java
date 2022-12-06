package com.geekbrains.demoboot.controllers;

import com.geekbrains.demoboot.entities.Item;
import com.geekbrains.demoboot.repositories.specification.ItemSpecs;
import com.geekbrains.demoboot.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

    private ItemsService itemsService;

    @Autowired
    public void setItemsService(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

//    @GetMapping
//    public String showItemsPage(Model model){
//        model.addAttribute("items", itemsService.getAllItems());
//        return "items";
//    }

    @GetMapping
    public String showItemsPage(Model model){
        Specification<Item> filter=Specification.where(null);
        filter=filter.and(ItemSpecs.costGreaterThanEq(40));
        List<Item> resultList=itemsService.getItemsWithPagingAndFiltering(filter, PageRequest.of(0,3)).getContent();
        model.addAttribute("items",resultList);

        return "items";
    }
}
