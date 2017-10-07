package com.example.playful.tasklostchilds.Comments;

import com.google.gson.annotations.Expose;

public class Comment {

    @Expose
    private long id;
    @Expose
    private String senderName;
    @Expose
    private String text;
    @Expose
    private String timeOfUpdate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimeOfUpdate() {
        return timeOfUpdate;
    }

    public void setTimeOfUpdate(String timeOfUpdate) {
        this.timeOfUpdate = timeOfUpdate;
    }
}
