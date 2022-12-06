package com.geekbrains.demoboot.controllers;

import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.entities.User;
import com.geekbrains.demoboot.repositories.specification.ProductSpec;
import com.geekbrains.demoboot.services.ProductsService;
import com.geekbrains.demoboot.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    private UserServiceImpl userService;


    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Principal principal,
                                   Model model,
                                   @RequestParam(value="page", required = false) Integer page,
                                   @RequestParam(value="word", required = false) String word,
                                   @RequestParam(value="minPrice", required = false) BigDecimal minPrice,
                                   @RequestParam(value="maxPrice", required = false) BigDecimal maxPrice) {
        Specification<Product> specification=Specification.where(null);

        if(principal!=null){
            User user=userService.findByUsername(principal.getName());
            model.addAttribute("username", user.getName());
        }

        if(page==null){
            page=1;
        }

        if(word!=null){
            specification=specification.and(ProductSpec.titleContains(word));
        }
        if(minPrice!=null){
            specification=specification.and(ProductSpec.priceGreaterThanEq(minPrice));
        }
        if(maxPrice!=null){
            specification=specification.and(ProductSpec.priceLesserThanEq(maxPrice));
        }
        Product product = new Product();
        model.addAttribute("products", productsService.getProductsWithPagingAndFiltering(specification, PageRequest.of(page-1,5)).getContent());
        model.addAttribute("product", product);
        model.addAttribute("word", word);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("top3List", productsService.getTop3List());
        return "products";
    }

    @GetMapping("/add")
    @Secured(value="ROLE_ADMIN")
    public String showAddProductForm(Model model){
        Product product=new Product();
        model.addAttribute("product", product);
        return "product-edit";
    }

    @GetMapping("/edit/{id}")
    @Secured(value="ROLE_ADMIN")
    public String showEditProductForm(Model model, @PathVariable(value="id") Long id){
        Product product=productsService.getById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/edit")
    @Secured(value="ROLE_ADMIN")
    public String addProduct(@ModelAttribute(value="product") Product product){
          productsService.add(product);
          return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        product=productsService.incrementViewsCounter(product);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(value = "id") Long id) {
        productsService.deleteById(id);
        return "redirect:/products";
    }


    // Old product Service
//    @GetMapping
//    public String showProductsList(Model model, @RequestParam(value="word", required = false) String word) {
//        Product product = new Product();
//        model.addAttribute("products", productsService.getAllProductsWithFilter(word));
//        model.addAttribute("product", product);
//        model.addAttribute("word", word);
//        return "products";
//    }
//
//    @GetMapping("/add")
//    public String showAddProductForm(Model model){
//        Product product=new Product();
//        model.addAttribute("product", product);
//        return "product-edit";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showEditProductForm(Model model, @PathVariable(value="id") Long id){
//        Product product=productsService.getById(id);
//        model.addAttribute("product", product);
//        return "product-edit";
//    }
//
//    @PostMapping("/edit")
//    public String addProduct(@ModelAttribute(value="product") Product product){
//          productsService.add(product);
//          return "redirect:/products";
//    }
//
//    @GetMapping("/show/{id}")
//    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
//        Product product = productsService.getById(id);
//        model.addAttribute("product", product);
//        return "product-page";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteById(@PathVariable(value = "id") Long id) {
//        productsService.deleteById(id);
//        return "redirect:/products";
//    }
}
