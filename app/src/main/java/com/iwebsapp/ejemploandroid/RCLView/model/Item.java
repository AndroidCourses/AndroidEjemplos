package com.iwebsapp.ejemploandroid.RCLView.model;

public class Item {
    private String text;
    private String description;
    private boolean isExpandable;

    public Item(String text, String description, boolean isExpandable) {
        this.text = text;
        this.description = description;
        this.isExpandable = isExpandable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
