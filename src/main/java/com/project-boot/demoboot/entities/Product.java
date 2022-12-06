package com.geekbrains.demoboot.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue
    @Column
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="price")
    private int price;

    @Column(name="views")
    private int views;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", views=" + views +
                '}';
    }
}
