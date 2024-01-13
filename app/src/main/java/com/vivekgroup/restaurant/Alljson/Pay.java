
package com.vivekgroup.restaurant.Alljson;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Pay {

    @SerializedName("payment_id")
    @Expose
    private String paymentId;
    @SerializedName("Food_id")
    @Expose
    private String foodId;
    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("User_id")
    @Expose
    private String userId;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;

    @SerializedName("Totalfood_price")
    @Expose
    private String totalfoodPrice;

    @SerializedName("Extra_Charge")
    @Expose
    private String extra_Charge;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getfoodId() {
        return foodId;
    }

    public void setfoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTotalfoodPrice() {
        return totalfoodPrice;
    }

    public void setTotalfoodPrice(String totalfoodPrice) {
        this.totalfoodPrice = totalfoodPrice;
    }

    public String getExtra_Charge() {
        return extra_Charge;
    }

    public void setExtra_Charge(String extra_Charge) {
        this.extra_Charge = extra_Charge;
    }
}
