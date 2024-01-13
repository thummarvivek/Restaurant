
package com.vivekgroup.restaurant.Alljson;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultCartFood {

    @SerializedName("CartFood")
    @Expose
    private List<CartFood> cartFood;

    public List<CartFood> getCartFood() {
        return cartFood;
    }

    public void setCartFood(List<CartFood> cartFood) {
        this.cartFood = cartFood;
    }

}
