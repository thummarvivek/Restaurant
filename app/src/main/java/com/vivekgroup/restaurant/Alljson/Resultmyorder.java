
package com.vivekgroup.restaurant.Alljson;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Resultmyorder {

    @SerializedName("myorder")
    @Expose
    private List<Myorder> myorder;

    public List<Myorder> getMyorder() {
        return myorder;
    }

    public void setMyorder(List<Myorder> myorder) {
        this.myorder = myorder;
    }

}
