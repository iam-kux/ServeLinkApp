package com.splinesoft.servelinkapp.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Booking {
    private String bookingId;
    private String clientId;
    private String clientName;
    private String providerId;
    private String providerName;
    private String providerImage;
    private String serviceId;
    private String serviceTitle;
    private String date;
    private String time;
    private String location;
    private String instructions;
    private double latitude;
    private double longitude;
    private double totalAmount;
    private double commission;
    private double providerEarning;
    private String status;
    private long completedAt;
    private long createdAt;
    private long updatedAt;

    public Booking() {
        this.status = "Pending";
    }

    public Booking(String bookingId, String clientId, String providerId,
                   String serviceId, String date, String time, String status) {
        this();
        this.bookingId = bookingId;
        this.clientId = clientId;
        this.providerId = providerId;
        this.serviceId = serviceId;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    @Exclude
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("bookingId", bookingId);
        map.put("clientId", clientId);
        map.put("clientName", clientName);
        map.put("providerId", providerId);
        map.put("providerName", providerName);
        map.put("providerImage", providerImage);
        map.put("serviceId", serviceId);
        map.put("serviceTitle", serviceTitle);
        map.put("date", date);
        map.put("time", time);
        map.put("location", location);
        map.put("instructions", instructions);
        map.put("latitude", latitude);
        map.put("longitude", longitude);
        map.put("totalAmount", totalAmount);
        map.put("commission", commission);
        map.put("providerEarning", providerEarning);
        map.put("status", status);
        map.put("completedAt", completedAt);
        map.put("createdAt", createdAt);
        map.put("updatedAt", ServerValue.TIMESTAMP);
        return map;
    }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getProviderId() { return providerId; }
    public void setProviderId(String providerId) { this.providerId = providerId; }

    public String getProviderName() { return providerName; }
    public void setProviderName(String providerName) { this.providerName = providerName; }

    public String getProviderImage() { return providerImage; }
    public void setProviderImage(String providerImage) { this.providerImage = providerImage; }

    public String getServiceId() { return serviceId; }
    public void setServiceId(String serviceId) { this.serviceId = serviceId; }

    public String getServiceTitle() { return serviceTitle; }
    public void setServiceTitle(String serviceTitle) { this.serviceTitle = serviceTitle; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public double getCommission() { return commission; }
    public void setCommission(double commission) { this.commission = commission; }

    public double getProviderEarning() { return providerEarning; }
    public void setProviderEarning(double providerEarning) { this.providerEarning = providerEarning; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public long getCompletedAt() { return completedAt; }
    public void setCompletedAt(long completedAt) { this.completedAt = completedAt; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public long getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(long updatedAt) { this.updatedAt = updatedAt; }
}
