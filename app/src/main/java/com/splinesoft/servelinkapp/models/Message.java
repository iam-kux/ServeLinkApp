package com.splinesoft.servelinkapp.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Message {
    private String messageId;
    private String chatId;
    private String senderId;
    private String receiverId;
    private String message;
    private String messageType;
    private String fileUrl;
    private String fileName;
    private boolean isRead;
    private long timestamp;
    private long deliveredAt;
    private long readAt;
    private boolean isDeleted;

    public Message() {
        this.messageType = "text";
        this.isRead = false;
        this.isDeleted = false;
    }

    public Message(String chatId, String senderId, String receiverId, String message, long timestamp) {
        this();
        this.chatId = chatId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.timestamp = timestamp;
    }

    @Exclude
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("chatId", chatId);
        map.put("senderId", senderId);
        map.put("receiverId", receiverId);
        map.put("message", message);
        map.put("messageType", messageType);
        map.put("fileUrl", fileUrl);
        map.put("fileName", fileName);
        map.put("isRead", isRead);
        map.put("timestamp", ServerValue.TIMESTAMP);
        map.put("deliveredAt", deliveredAt);
        map.put("readAt", readAt);
        map.put("isDeleted", isDeleted);
        return map;
    }

    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }

    public String getChatId() { return chatId; }
    public void setChatId(String chatId) { this.chatId = chatId; }

    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getReceiverId() { return receiverId; }
    public void setReceiverId(String receiverId) { this.receiverId = receiverId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getMessageType() { return messageType; }
    public void setMessageType(String messageType) { this.messageType = messageType; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public long getDeliveredAt() { return deliveredAt; }
    public void setDeliveredAt(long deliveredAt) { this.deliveredAt = deliveredAt; }

    public long getReadAt() { return readAt; }
    public void setReadAt(long readAt) { this.readAt = readAt; }

    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean deleted) { isDeleted = deleted; }
}
