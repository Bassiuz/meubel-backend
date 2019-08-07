package com.bassiuz.meubel.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bassiuz.meubel.responses.MeubelResponse;

@Entity
public class Meubel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;

    private String matchingPersonName;

    private String name;
    private String description;
    private String shopUrl;
    private String imageUrl;
    private Shop shop;
    private int score;

    public Meubel()
    {
        // empty constructor for persistence
    }

    public Meubel(MeubelResponse meubelResponse)
    {
        name = meubelResponse.getName();
        description = meubelResponse.getDescription();
        shopUrl = meubelResponse.getShopUrl();
        imageUrl = meubelResponse.getImageUrl();
        shop = meubelResponse.getShop();
        score = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void scoreMeubel()
    {
        this.score++;
    }

    public int getScore()
    {
        return score;
    }

    public String getMatchingPersonName() {
        return matchingPersonName;
    }

    public void setMatchingPersonName(String matchingPersonName) {
        this.matchingPersonName = matchingPersonName;
    }

    

}