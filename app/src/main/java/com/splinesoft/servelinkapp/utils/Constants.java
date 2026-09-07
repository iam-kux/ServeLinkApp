package com.splinesoft.servelinkapp.utils;

public final class Constants {

    private Constants() {}

    // Database References
    public static final String REF_USERS = "Users";
    public static final String REF_USER_PROFILES = "UserProfiles";
    public static final String REF_CATEGORIES = "Categories";
    public static final String REF_JOBS = "Jobs";
    public static final String REF_APPLICATIONS = "Applications";
    public static final String REF_CONTRACTS = "Contracts";
    public static final String REF_BOOKINGS = "Bookings";
    public static final String REF_CHATS = "Chats";
    public static final String REF_MESSAGES = "Messages";
    public static final String REF_NOTIFICATIONS = "Notifications";
    public static final String REF_REVIEWS = "Reviews";
    public static final String REF_WALLETS = "Wallets";
    public static final String REF_TRANSACTIONS = "Transactions";
    public static final String REF_PORTFOLIO_ITEMS = "PortfolioItems";
    public static final String REF_SAVED_JOBS = "SavedJobs";
    public static final String REF_SAVED_FREELANCERS = "SavedFreelancers";
    public static final String REF_USER_TYPING = "UserTyping";
    public static final String REF_ONLINE_STATUS = "OnlineStatus";

    // Storage Paths
    public static final String STORAGE_PROFILE_IMAGES = "profile_images";
    public static final String STORAGE_PORTFOLIO_IMAGES = "portfolio_images";
    public static final String STORAGE_CHAT_IMAGES = "chat_images";
    public static final String STORAGE_CHAT_FILES = "chat_files";

    // User Roles
    public static final String ROLE_CLIENT = "client";
    public static final String ROLE_PROVIDER = "provider";
    public static final String ROLE_ADMIN = "admin";

    // Job Status
    public static final String JOB_STATUS_OPEN = "open";
    public static final String JOB_STATUS_IN_REVIEW = "in_review";
    public static final String JOB_STATUS_HIRED = "hired";
    public static final String JOB_STATUS_IN_PROGRESS = "in_progress";
    public static final String JOB_STATUS_COMPLETED = "completed";
    public static final String JOB_STATUS_CANCELLED = "cancelled";

    // Application Status
    public static final String APP_STATUS_PENDING = "pending";
    public static final String APP_STATUS_ACCEPTED = "accepted";
    public static final String APP_STATUS_REJECTED = "rejected";

    // Contract Status
    public static final String CONTRACT_STATUS_ACTIVE = "active";
    public static final String CONTRACT_STATUS_COMPLETED = "completed";
    public static final String CONTRACT_STATUS_CANCELLED = "cancelled";
    public static final String CONTRACT_STATUS_DISPUTED = "disputed";

    // Booking Status
    public static final String BOOKING_STATUS_PENDING = "pending";
    public static final String BOOKING_STATUS_ACCEPTED = "accepted";
    public static final String BOOKING_STATUS_ON_THE_WAY = "on_the_way";
    public static final String BOOKING_STATUS_STARTED = "started";
    public static final String BOOKING_STATUS_COMPLETED = "completed";
    public static final String BOOKING_STATUS_CANCELLED = "cancelled";

    // Provider Availability
    public static final String AVAILABILITY_AVAILABLE = "available";
    public static final String AVAILABILITY_BUSY = "busy";
    public static final String AVAILABILITY_UNAVAILABLE = "unavailable";

    // Message Types
    public static final String MSG_TYPE_TEXT = "text";
    public static final String MSG_TYPE_IMAGE = "image";
    public static final String MSG_TYPE_FILE = "file";

    // Notification Types
    public static final String NOTIF_TYPE_MESSAGE = "message";
    public static final String NOTIF_TYPE_APPLICATION = "application";
    public static final String NOTIF_TYPE_JOB = "job";
    public static final String NOTIF_TYPE_REVIEW = "review";
    public static final String NOTIF_TYPE_PAYMENT = "payment";
    public static final String NOTIF_TYPE_BOOKING = "booking";

    // Transaction Types
    public static final String TXN_TYPE_DEPOSIT = "deposit";
    public static final String TXN_TYPE_WITHDRAWAL = "withdrawal";
    public static final String TXN_TYPE_PAYMENT = "payment";
    public static final String TXN_TYPE_COMMISSION = "commission";
    public static final String TXN_TYPE_EARNING = "earning";

    // Platform Commission
    public static final double PLATFORM_COMMISSION_RATE = 0.10;

    // Pagination
    public static final int PAGE_SIZE = 20;

    // SharedPreferences
    public static final String PREFS_NAME = "ServeLinkPrefs";
    public static final String PREF_IS_FIRST_RUN = "isFirstRun";
    public static final String PREF_USER_ID = "userId";
    public static final String PREF_USER_ROLE = "userRole";
    public static final String PREF_USER_NAME = "userName";
    public static final String PREF_USER_EMAIL = "userEmail";
    public static final String PREF_USER_IMAGE = "userImage";
    public static final String PREF_IS_LOGGED_IN = "isLoggedIn";

    // Intent Extras
    public static final String EXTRA_USER_ID = "userId";
    public static final String EXTRA_JOB_ID = "jobId";
    public static final String EXTRA_SERVICE_ID = "serviceId";
    public static final String EXTRA_PROVIDER_ID = "providerId";
    public static final String EXTRA_CLIENT_ID = "clientId";
    public static final String EXTRA_CHAT_ID = "chatId";
    public static final String EXTRA_RECEIVER_ID = "receiverId";
    public static final String EXTRA_RECEIVER_NAME = "receiverName";
    public static final String EXTRA_CONTRACT_ID = "contractId";
    public static final String EXTRA_BOOKING_ID = "bookingId";
    public static final String EXTRA_APPLICATION_ID = "applicationId";
    public static final String EXTRA_ROLE = "role";

    // Categories - Local Services
    public static final String CAT_PLUMBER = "Plumber";
    public static final String CAT_ELECTRICIAN = "Electrician";
    public static final String CAT_MECHANIC = "Mechanic";
    public static final String CAT_CLEANER = "Cleaner";
    public static final String CAT_TUTOR = "Tutor";
    public static final String CAT_BUILDER = "Builder";

    // Categories - Remote Services
    public static final String CAT_GRAPHIC_DESIGNER = "Graphic Designer";
    public static final String CAT_DEVELOPER = "Developer";
    public static final String CAT_WRITER = "Writer";
    public static final String CAT_VIRTUAL_ASSISTANT = "Virtual Assistant";
    public static final String CAT_VIDEO_EDITOR = "Video Editor";
    public static final String CAT_DIGITAL_MARKETER = "Digital Marketer";

    // Category Types
    public static final String CAT_TYPE_LOCAL = "local";
    public static final String CAT_TYPE_REMOTE = "remote";

    // Google Sign In Request Code
    public static final int RC_SIGN_IN = 9001;

    // Image Pick Request Code
    public static final int RC_PICK_IMAGE = 1001;
    public static final int RC_PICK_FILE = 1002;
    public static final int RC_CAMERA = 1003;

    // Firebase database/collection references mapping for backwards compatibility
    public static final String USERS_REF = REF_USERS;
    public static final String BOOKINGS_REF = REF_BOOKINGS;
    public static final String SERVICES_REF = "Services";
    public static final String REF_SERVICES = "Services";
    public static final String MESSAGES_REF = REF_MESSAGES;
    public static final String STATUS_PENDING = BOOKING_STATUS_PENDING;
}
