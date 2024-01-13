
package com.vivekgroup.restaurant.Alljson;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultFoodTable {

    @SerializedName("FoodTable")
    @Expose
    private List<FoodTable> foodTable;

    public List<FoodTable> getFoodTable() {
        return foodTable;
    }

    public void setFoodTable(List<FoodTable> foodTable) {
        this.foodTable = foodTable;
    }

}
