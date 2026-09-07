package com.splinesoft.servelinkapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.adapters.ChatListAdapter;
import com.splinesoft.servelinkapp.data.repository.ChatRepository;
import com.splinesoft.servelinkapp.models.Chat;
import com.splinesoft.servelinkapp.models.User;
import com.splinesoft.servelinkapp.ui.chat.ChatActivity;
import com.splinesoft.servelinkapp.utils.SessionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessagesFragment extends Fragment implements ChatListAdapter.OnChatClickListener {

    private RecyclerView rvChats;
    private ProgressBar progressBar;
    private LinearLayout layoutEmpty;

    private ChatRepository chatRepository;
    private SessionManager sessionManager;
    private List<Chat> chatList;
    private ChatListAdapter adapter;
    private String currentUserId;
    private ValueEventListener chatsListener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chatRepository = new ChatRepository();
        sessionManager = new SessionManager(requireContext());
        currentUserId = sessionManager.getUserId();

        rvChats = view.findViewById(R.id.rvChats);
        progressBar = view.findViewById(R.id.progressBar);
        layoutEmpty = view.findViewById(R.id.layoutEmpty);

        chatList = new ArrayList<>();
        adapter = new ChatListAdapter(chatList, currentUserId, this);
        rvChats.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvChats.setAdapter(adapter);

        loadChats();
    }

    private void loadChats() {
        if (currentUserId.isEmpty()) return;

        progressBar.setVisibility(View.VISIBLE);
        chatsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressBar.setVisibility(View.GONE);
                chatList.clear();
                for (DataSnapshot child : snapshot.getChildren()) {
                    Chat chat = child.getValue(Chat.class);
                    if (chat != null && chat.getParticipants() != null && chat.getParticipants().containsKey(currentUserId)) {
                        chatList.add(chat);
                    }
                }
                
                // Sort chats by last updated time (descending)
                Collections.sort(chatList, (c1, c2) -> Long.compare(c2.getUpdatedAt(), c1.getUpdatedAt()));
                
                adapter.notifyDataSetChanged();
                updateViewsState();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "Failed to load chats: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                updateViewsState();
            }
        };

        chatRepository.listenForChats(currentUserId, chatsListener);
    }

    private void updateViewsState() {
        if (chatList.isEmpty()) {
            layoutEmpty.setVisibility(View.VISIBLE);
            rvChats.setVisibility(View.GONE);
        } else {
            layoutEmpty.setVisibility(View.GONE);
            rvChats.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onChatClick(Chat chat, User otherUser) {
        Intent intent = new Intent(requireContext(), ChatActivity.class);
        intent.putExtra("chatId", chat.getChatId());
        intent.putExtra("otherUserId", otherUser.getUserId());
        intent.putExtra("otherUserName", otherUser.getName());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (chatsListener != null) {
            chatRepository.removeChatsListener(chatsListener);
        }
    }
}
