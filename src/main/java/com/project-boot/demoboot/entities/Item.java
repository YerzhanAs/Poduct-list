package com.geekbrains.demoboot.entities;

import javax.persistence.*;

@Entity
@Table(name="items")
public class Item {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="cost")
    private int cost;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Item(){
    }

    public Item(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("Item [id=%d title=%s cost=%d]", id,title,cost);
    }
}
