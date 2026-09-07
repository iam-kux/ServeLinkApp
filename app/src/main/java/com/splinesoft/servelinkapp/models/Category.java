package com.splinesoft.servelinkapp.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Category {
    private String categoryId;
    private String name;
    private String icon; // Icon name (e.g. drawable resource name) or URL
    private String type; // "local" or "remote"
    private long createdAt;

    public Category() {
    }

    public Category(String categoryId, String name, String icon, String type) {
        this.categoryId = categoryId;
        this.name = name;
        this.icon = icon;
        this.type = type;
        this.createdAt = System.currentTimeMillis();
    }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}
