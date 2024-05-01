
package com.vivekgroup.restaurant.Alljson;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultGallery {

    @SerializedName("Gallery")
    @Expose
    private List<Gallery> gallery;

    public List<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

}
