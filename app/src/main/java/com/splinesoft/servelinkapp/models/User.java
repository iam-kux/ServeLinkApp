package com.splinesoft.servelinkapp.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IgnoreExtraProperties
public class User {
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String profileImage;
    private String bio;
    private String location;
    private double latitude;
    private double longitude;
    private double serviceAreaRadius;
    private List<String> skills;
    private double hourlyRate;
    private double fixedRate;
    private String availability;
    private String experience;
    private String certifications;
    private float rating;
    private int ratingCount;
    private int totalJobs;
    private int completedJobs;
    private double totalEarnings;
    private boolean isOnline;
    private long lastSeen;
    private String fcmToken;
    private boolean isVerified;
    private boolean isSuspended;
    private boolean isAdmin;
    private long createdAt;
    private long updatedAt;

    public User() {
        this.skills = new ArrayList<>();
        this.rating = 0.0f;
        this.ratingCount = 0;
        this.totalJobs = 0;
        this.completedJobs = 0;
        this.totalEarnings = 0;
        this.isOnline = false;
        this.isVerified = false;
        this.isSuspended = false;
        this.isAdmin = false;
    }

    public User(String userId, String name, String email, String phone, String role) {
        this();
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.profileImage = "";
        this.bio = "";
        this.location = "";
    }

    @Exclude
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("name", name);
        map.put("email", email);
        map.put("phone", phone);
        map.put("role", role);
        map.put("profileImage", profileImage);
        map.put("bio", bio);
        map.put("location", location);
        map.put("latitude", latitude);
        map.put("longitude", longitude);
        map.put("serviceAreaRadius", serviceAreaRadius);
        map.put("skills", skills);
        map.put("hourlyRate", hourlyRate);
        map.put("fixedRate", fixedRate);
        map.put("availability", availability);
        map.put("experience", experience);
        map.put("certifications", certifications);
        map.put("rating", rating);
        map.put("ratingCount", ratingCount);
        map.put("totalJobs", totalJobs);
        map.put("completedJobs", completedJobs);
        map.put("totalEarnings", totalEarnings);
        map.put("isOnline", isOnline);
        map.put("lastSeen", lastSeen);
        map.put("fcmToken", fcmToken);
        map.put("isVerified", isVerified);
        map.put("isSuspended", isSuspended);
        map.put("isAdmin", isAdmin);
        map.put("createdAt", createdAt);
        map.put("updatedAt", ServerValue.TIMESTAMP);
        return map;
    }

    // Getters and Setters
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

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getServiceAreaRadius() { return serviceAreaRadius; }
    public void setServiceAreaRadius(double serviceAreaRadius) { this.serviceAreaRadius = serviceAreaRadius; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    public double getFixedRate() { return fixedRate; }
    public void setFixedRate(double fixedRate) { this.fixedRate = fixedRate; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }

    public String getCertifications() { return certifications; }
    public void setCertifications(String certifications) { this.certifications = certifications; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public int getRatingCount() { return ratingCount; }
    public void setRatingCount(int ratingCount) { this.ratingCount = ratingCount; }

    public int getTotalJobs() { return totalJobs; }
    public void setTotalJobs(int totalJobs) { this.totalJobs = totalJobs; }

    public int getCompletedJobs() { return completedJobs; }
    public void setCompletedJobs(int completedJobs) { this.completedJobs = completedJobs; }

    public double getTotalEarnings() { return totalEarnings; }
    public void setTotalEarnings(double totalEarnings) { this.totalEarnings = totalEarnings; }

    public boolean isOnline() { return isOnline; }
    public void setOnline(boolean online) { isOnline = online; }

    public long getLastSeen() { return lastSeen; }
    public void setLastSeen(long lastSeen) { this.lastSeen = lastSeen; }

    public String getFcmToken() { return fcmToken; }
    public void setFcmToken(String fcmToken) { this.fcmToken = fcmToken; }

    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { isVerified = verified; }

    public boolean isSuspended() { return isSuspended; }
    public void setSuspended(boolean suspended) { isSuspended = suspended; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public long getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(long updatedAt) { this.updatedAt = updatedAt; }
}
