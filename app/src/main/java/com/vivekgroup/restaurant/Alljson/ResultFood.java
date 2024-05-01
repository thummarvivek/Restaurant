
package com.vivekgroup.restaurant.Alljson;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultFood {

    @SerializedName("Food")
    @Expose
    private List<Food> food;

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

}
