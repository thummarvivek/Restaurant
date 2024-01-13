
package com.vivekgroup.restaurant.Alljson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResultFeedback {

    @SerializedName("feed")
    @Expose
    private List<Feed> feed;

    public List<Feed> getFeed() {
        return feed;
    }

    public void setFeed(List<Feed> feed) {
        this.feed = feed;
    }

}
