package com.example.musicapp.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicCategoryToday {

    @SerializedName("Category")
    @Expose
    private List<Category> category;
    @SerializedName("Topic")
    @Expose
    private List<Topic> topic;

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Topic> getTopic() {
        return topic;
    }

    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }
}
