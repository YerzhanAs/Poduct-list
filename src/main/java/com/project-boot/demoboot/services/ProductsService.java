package com.geekbrains.demoboot.services;

import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public Product incrementViewsCounter(Product product){
        product.setViews(product.getViews()+1);
        return productRepository.save(product);
    }

    public List<Product> getTop3List(){
        return productRepository.getTop3Products().stream().limit(3).collect(Collectors.toList());
    }

    public Page getProductsWithPagingAndFiltering(Specification<Product> specifications, Pageable pageable){
//        List<ItemProjection> itemProjections=itemRepository.getProjections();
//        itemProjections.stream().forEach(O-> System.out.println(O.title()+" "+O.cost()));
        return productRepository.findAll(specifications, pageable);
    }


    //OldRepositiory
//    public Product getById(Long id) {
//        return productRepository.findById(id);
//    }
//
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    public List<Product> getAllProductsWithFilter(String word) {
//        List<Product> fullList=productRepository.findAll();
//        if(word==null){
//            return fullList;
//        }
//        return fullList.stream().filter(p->p.getTitle().contains(word)).collect(Collectors.toList());
//    }
//
//    public void add(Product product) {
//        productRepository.save(product);
//    }
//
//    public void deleteById(Long id){
//        productRepository.remove(id);
//    }
}
