package com.stdio.hue.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TranHuuPhuc on 2019-05-12.
 */
public class TVShow extends Movie {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
