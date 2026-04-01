package com.splinesoft.servelinkapp.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.models.Message;
import com.splinesoft.servelinkapp.utils.Constants;
import com.splinesoft.servelinkapp.utils.FirebaseHelper;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rvMessages;
    private EditText etMessage;
    private ImageView btnSend;
    private String receiverId, senderId, chatId;
    // private ChatAdapter chatAdapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Toolbar toolbar = findViewById(R.id.chatToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Chat");
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        receiverId = getIntent().getStringExtra("receiverId");
        if (FirebaseHelper.getAuth().getCurrentUser() != null) {
            senderId = FirebaseHelper.getAuth().getCurrentUser().getUid();
        } else {
            senderId = "dummy_sender";
        }

        chatId = senderId.compareTo(receiverId) > 0 ? senderId + "_" + receiverId : receiverId + "_" + senderId;

        rvMessages = findViewById(R.id.rvMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        messageList = new ArrayList<>();
        // chatAdapter = new ChatAdapter(messageList, senderId);
        
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        rvMessages.setLayoutManager(layoutManager);
        // rvMessages.setAdapter(chatAdapter);

        loadMessages();

        btnSend.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String msgText = etMessage.getText().toString().trim();
        if (TextUtils.isEmpty(msgText)) return;

        DatabaseReference ref = FirebaseHelper.getDatabase().getReference(Constants.MESSAGES_REF).child(chatId);
        String msgId = ref.push().getKey();

        Message message = new Message(chatId, senderId, receiverId, msgText, System.currentTimeMillis());

        if (msgId != null) {
            ref.child(msgId).setValue(message);
        }
        etMessage.setText("");
    }

    private void loadMessages() {
        DatabaseReference ref = FirebaseHelper.getDatabase().getReference(Constants.MESSAGES_REF).child(chatId);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                messageList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Message msg = ds.getValue(Message.class);
                    if (msg != null) messageList.add(msg);
                }
                // chatAdapter.notifyDataSetChanged();
                rvMessages.scrollToPosition(messageList.size() - 1);
            }

            @Override
            public void onCancelled(DatabaseError error) { }
        });
    }
}
