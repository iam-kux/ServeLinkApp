package com.splinesoft.servelinkapp.models;

public class User {
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String profileImage;
    private String location;
    private float rating;

    public User() {
        // Required empty public constructor for Firebase
    }

    public User(String userId, String name, String email, String phone, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.profileImage = "";
        this.location = "";
        this.rating = 0.0f;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getProfileImage() { return profileImage; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
}
