
package com.vivekgroup.restaurant.Alljson;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CartFood {

    @SerializedName("Food_name")
    @Expose
    private String foodName;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("Food_price")
    @Expose
    private String foodPrice;
    @SerializedName("Food_id")
    @Expose
    private String foodId;
    @SerializedName("cart_id")
    @Expose
    private String cartId;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

}