package com.geekbrains.demoboot.controllers;

import com.geekbrains.demoboot.entities.ItemProjection;
import com.geekbrains.demoboot.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/main")
public class MainController {

    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //    @GetMapping
//    public String index() {
//        return "index";
//    }
//
//    @GetMapping("/secured")
//    public String secured() {
//        return "index";
//    }
//

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }


    @GetMapping("/index")
    public String doSomething() {
        List<ItemProjection> itemProjection=itemRepository.findItemsProjectionsByCost(50);
        System.out.println(itemProjection);
        return "index";
    }

    @GetMapping("/hello15")
    @ResponseBody
    public String showForm2(){
        return "Hello";
    }

    @GetMapping("/hello")
    public String helloRequest(Model model, @RequestParam(value = "name") String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/hello/(name)")
    public String helloRequest2(Model model, @PathVariable(value = "name") String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/addcat")
    public String showAddCatForm(Model model) {
        Cat cat = new Cat(1L, null, null);
        model.addAttribute("cat", cat);
        return "cat-form";
    }

    @PostMapping("/addcat")
    public String showAddCatForm(@ModelAttribute(value = "cat") Cat cat) {
        System.out.println(cat.getId() + " " + cat.getName() + " " + cat.getColor());
        return "redirect:/index";
    }

    @PostMapping("/form")
    public String saveForm(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {
        System.out.println(name);
        System.out.println(email);
        return "redirect:/index";
    }

    @GetMapping("/form")
    public String showForm(){
        return "simple-form";
    }
}
