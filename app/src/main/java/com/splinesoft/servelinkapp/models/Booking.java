package com.splinesoft.servelinkapp.models;

public class Booking {
    private String bookingId;
    private String clientId;
    private String providerId;
    private String serviceId;
    private String date;
    private String time;
    private String status;

    public Booking() {}

    public Booking(String bookingId, String clientId, String providerId, String serviceId, String date, String time, String status) {
        this.bookingId = bookingId;
        this.clientId = clientId;
        this.providerId = providerId;
        this.serviceId = serviceId;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getProviderId() { return providerId; }
    public void setProviderId(String providerId) { this.providerId = providerId; }

    public String getServiceId() { return serviceId; }
    public void setServiceId(String serviceId) { this.serviceId = serviceId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
