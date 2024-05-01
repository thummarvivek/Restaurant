
package com.vivekgroup.restaurant.Alljson;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultorderPay {

    @SerializedName("OrderPayment")
    @Expose
    private List<OrderPayment> orderPayment;

    public List<OrderPayment> getOrderPayment() {
        return orderPayment;
    }

    public void setOrderPayment(List<OrderPayment> orderPayment) {
        this.orderPayment = orderPayment;
    }

}
