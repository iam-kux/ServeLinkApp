package com.splinesoft.servelinkapp.models;

public class Service {
    private String serviceId;
    private String providerId;
    private String title;
    private String description;
    private String category;
    private double price;
    private String image;
    private String location;
    private float rating;

    public Service() {}

    public Service(String serviceId, String providerId, String title, String description, String category, double price, String location) {
        this.serviceId = serviceId;
        this.providerId = providerId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.image = "";
        this.location = location;
        this.rating = 0.0f;
    }

    public String getServiceId() { return serviceId; }
    public void setServiceId(String serviceId) { this.serviceId = serviceId; }

    public String getProviderId() { return providerId; }
    public void setProviderId(String providerId) { this.providerId = providerId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
}
