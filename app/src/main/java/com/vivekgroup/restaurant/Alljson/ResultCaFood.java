
package com.vivekgroup.restaurant.Alljson;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultCaFood {

    @SerializedName("CaFood")
    @Expose
    private List<CaFood> caFood;

    public List<CaFood> getCaFood() {
        return caFood;
    }

    public void setCaFood(List<CaFood> caFood) {
        this.caFood = caFood;
    }

}
