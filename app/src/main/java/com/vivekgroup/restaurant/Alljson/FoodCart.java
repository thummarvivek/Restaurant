
package com.vivekgroup.restaurant.Alljson;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FoodCart {

    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("User_id")
    @Expose
    private String userId;
    @SerializedName("Food_id")
    @Expose
    private String foodId;
    @SerializedName("status")
    @Expose
    private String status;

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

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
