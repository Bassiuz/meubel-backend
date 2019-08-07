package com.bassiuz.meubel.responses;

import java.util.ArrayList;
import java.util.List;

import com.bassiuz.meubel.domain.Meubel;
import com.bassiuz.meubel.domain.Shop;

public class MeubelResponse {

   private String name;
   private String description;
   private String shopUrl;
   private String imageUrl;
   private Shop shop;
   private long id;

    public MeubelResponse()
    {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static List<MeubelResponse> fromMeubelList(List<Meubel> meubels)
    {
        ArrayList<MeubelResponse> response = new ArrayList<>();
        for (Meubel meubel : meubels)
        {
            response.add(MeubelResponse.fromMeubel(meubel));
        }
        return response;
    }

    public static MeubelResponse fromMeubel(Meubel meubel) {
        MeubelResponse meubelResponse = new MeubelResponse();
        meubelResponse.setId(meubel.getId());
        meubelResponse.setName(meubel.getName());
        meubelResponse.setDescription(meubel.getDescription());
        meubelResponse.setImageUrl(meubel.getImageUrl());
        meubelResponse.setShopUrl(meubel.getShopUrl());
        meubelResponse.setShop(meubel.getShop());
        return meubelResponse;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((shop == null) ? 0 : shop.hashCode());
        result = prime * result + ((shopUrl == null) ? 0 : shopUrl.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MeubelResponse other = (MeubelResponse) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (imageUrl == null) {
            if (other.imageUrl != null)
                return false;
        } else if (!imageUrl.equals(other.imageUrl))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (shop != other.shop)
            return false;
        if (shopUrl == null) {
            if (other.shopUrl != null)
                return false;
        } else if (!shopUrl.equals(other.shopUrl))
            return false;
        return true;
    }

    
    
}
