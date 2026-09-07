package com.splinesoft.servelinkapp.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public final class FirebaseHelper {

    private static FirebaseHelper instance;
    private final FirebaseAuth auth;
    private final FirebaseDatabase database;
    private final FirebaseStorage storage;

    private FirebaseHelper() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    public static synchronized FirebaseHelper getInstance() {
        if (instance == null) {
            instance = new FirebaseHelper();
        }
        return instance;
    }

    // Auth
    public static FirebaseAuth getAuth() {
        return getInstance().auth;
    }

    public static FirebaseDatabase getDatabase() {
        return getInstance().database;
    }

    public FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }

    public String getCurrentUserId() {
        FirebaseUser user = auth.getCurrentUser();
        return user != null ? user.getUid() : null;
    }

    // Database References
    public DatabaseReference getDbReference() {
        return database.getReference();
    }

    public DatabaseReference getUsersRef() {
        return database.getReference(Constants.REF_USERS);
    }

    public DatabaseReference getUserRef(String userId) {
        return database.getReference(Constants.REF_USERS).child(userId);
    }

    public DatabaseReference getUserProfilesRef() {
        return database.getReference(Constants.REF_USER_PROFILES);
    }

    public DatabaseReference getUserProfileRef(String userId) {
        return database.getReference(Constants.REF_USER_PROFILES).child(userId);
    }

    public DatabaseReference getCategoriesRef() {
        return database.getReference(Constants.REF_CATEGORIES);
    }

    public DatabaseReference getJobsRef() {
        return database.getReference(Constants.REF_JOBS);
    }

    public DatabaseReference getJobRef(String jobId) {
        return database.getReference(Constants.REF_JOBS).child(jobId);
    }

    public DatabaseReference getServicesRef() {
        return database.getReference(Constants.REF_SERVICES);
    }

    public DatabaseReference getServiceRef(String serviceId) {
        return database.getReference(Constants.REF_SERVICES).child(serviceId);
    }

    public DatabaseReference getApplicationsRef() {
        return database.getReference(Constants.REF_APPLICATIONS);
    }

    public DatabaseReference getApplicationRef(String applicationId) {
        return database.getReference(Constants.REF_APPLICATIONS).child(applicationId);
    }

    public DatabaseReference getContractsRef() {
        return database.getReference(Constants.REF_CONTRACTS);
    }

    public DatabaseReference getContractRef(String contractId) {
        return database.getReference(Constants.REF_CONTRACTS).child(contractId);
    }

    public DatabaseReference getBookingsRef() {
        return database.getReference(Constants.REF_BOOKINGS);
    }

    public DatabaseReference getBookingRef(String bookingId) {
        return database.getReference(Constants.REF_BOOKINGS).child(bookingId);
    }

    public DatabaseReference getChatsRef() {
        return database.getReference(Constants.REF_CHATS);
    }

    public DatabaseReference getChatRef(String chatId) {
        return database.getReference(Constants.REF_CHATS).child(chatId);
    }

    public DatabaseReference getMessagesRef(String chatId) {
        return database.getReference(Constants.REF_MESSAGES).child(chatId);
    }

    public DatabaseReference getNotificationsRef(String userId) {
        return database.getReference(Constants.REF_NOTIFICATIONS).child(userId);
    }

    public DatabaseReference getReviewsRef() {
        return database.getReference(Constants.REF_REVIEWS);
    }

    public DatabaseReference getWalletRef(String userId) {
        return database.getReference(Constants.REF_WALLETS).child(userId);
    }

    public DatabaseReference getTransactionsRef(String userId) {
        return database.getReference(Constants.REF_TRANSACTIONS).child(userId);
    }

    public DatabaseReference getPortfolioRef(String userId) {
        return database.getReference(Constants.REF_PORTFOLIO_ITEMS).child(userId);
    }

    public DatabaseReference getSavedJobsRef(String userId) {
        return database.getReference(Constants.REF_SAVED_JOBS).child(userId);
    }

    public DatabaseReference getSavedFreelancersRef(String userId) {
        return database.getReference(Constants.REF_SAVED_FREELANCERS).child(userId);
    }

    public DatabaseReference getTypingRef(String chatId) {
        return database.getReference(Constants.REF_USER_TYPING).child(chatId);
    }

    public DatabaseReference getOnlineStatusRef(String userId) {
        return database.getReference(Constants.REF_ONLINE_STATUS).child(userId);
    }

    // Storage References
    public StorageReference getStorageRef() {
        return storage.getReference();
    }

    public StorageReference getProfileImageRef(String userId) {
        return storage.getReference(Constants.STORAGE_PROFILE_IMAGES).child(userId + ".jpg");
    }

    public StorageReference getPortfolioImageRef(String userId, String itemId) {
        return storage.getReference(Constants.STORAGE_PORTFOLIO_IMAGES).child(userId).child(itemId + ".jpg");
    }

    public StorageReference getChatImageRef(String chatId, String messageId) {
        return storage.getReference(Constants.STORAGE_CHAT_IMAGES).child(chatId).child(messageId + ".jpg");
    }

    public StorageReference getChatFileRef(String chatId, String fileName) {
        return storage.getReference(Constants.STORAGE_CHAT_FILES).child(chatId).child(fileName);
    }

    // Generate unique chat ID between two users
    public String generateChatId(String userId1, String userId2) {
        return userId1.compareTo(userId2) < 0
                ? userId1 + "_" + userId2
                : userId2 + "_" + userId1;
    }
}
