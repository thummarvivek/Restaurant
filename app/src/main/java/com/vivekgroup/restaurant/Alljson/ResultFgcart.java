
package com.vivekgroup.restaurant.Alljson;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultFgcart {

    @SerializedName("FoodCart")
    @Expose
    private List<FoodCart> foodCart;

    public List<FoodCart> getFoodCart() {
        return foodCart;
    }

    public void setFoodCart(List<FoodCart> foodCart) {
        this.foodCart = foodCart;
    }

}
