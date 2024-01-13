
package com.vivekgroup.restaurant.Alljson;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultFoodPay {

    @SerializedName("Pay")
    @Expose
    private List<Pay> pay;

    public List<Pay> getPay() {
        return pay;
    }

    public void setPay(List<Pay> pay) {
        this.pay = pay;
    }

}
