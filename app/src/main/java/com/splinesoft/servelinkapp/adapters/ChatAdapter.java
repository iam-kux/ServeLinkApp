package com.splinesoft.servelinkapp.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.models.Message;
import com.splinesoft.servelinkapp.utils.Constants;
import com.splinesoft.servelinkapp.utils.DateTimeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_SENT = 1;
    private static final int TYPE_RECEIVED = 2;

    private final List<Message> messageList;
    private final String currentUserId;

    public ChatAdapter(List<Message> messageList, String currentUserId) {
        this.messageList = messageList;
        this.currentUserId = currentUserId;
    }

    @Override
    public int getItemViewType(int position) {
        if (messageList.get(position).getSenderId().equals(currentUserId)) {
            return TYPE_SENT;
        } else {
            return TYPE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_SENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);

        if (getItemViewType(position) == TYPE_SENT) {
            bindMessageHolder((SentMessageHolder) holder, message);
        } else {
            bindMessageHolder((ReceivedMessageHolder) holder, message);
        }
    }

    private void bindMessageHolder(MessageViewHolder holder, Message message) {
        // Set timestamp
        holder.tvTimestamp.setText(DateTimeUtil.formatChatTime(message.getTimestamp()));

        // Check message type
        if (Constants.MSG_TYPE_IMAGE.equals(message.getMessageType())) {
            holder.tvMessage.setVisibility(View.GONE);
            holder.imgMessage.setVisibility(View.VISIBLE);
            if (message.getFileUrl() != null && !message.getFileUrl().isEmpty()) {
                Picasso.get()
                        .load(message.getFileUrl())
                        .placeholder(R.drawable.baseline_camera_alt_24)
                        .error(R.drawable.baseline_camera_alt_24)
                        .into(holder.imgMessage);
            }
            // Allow image viewing
            holder.imgMessage.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(message.getFileUrl()));
                v.getContext().startActivity(intent);
            });
        } else if (Constants.MSG_TYPE_FILE.equals(message.getMessageType())) {
            holder.imgMessage.setVisibility(View.GONE);
            holder.tvMessage.setVisibility(View.VISIBLE);
            holder.tvMessage.setText("Attachment: " + message.getFileName() + "\n(Tap to download)");
            // Allow file downloading/opening
            holder.tvMessage.setOnClickListener(v -> {
                if (message.getFileUrl() != null && !message.getFileUrl().isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(message.getFileUrl()));
                    v.getContext().startActivity(intent);
                }
            });
        } else {
            // Text message
            holder.imgMessage.setVisibility(View.GONE);
            holder.tvMessage.setVisibility(View.VISIBLE);
            holder.tvMessage.setText(message.getMessage());
            holder.tvMessage.setOnClickListener(null); // Clear click listener
        }
    }

    @Override
    public int getItemCount() {
        return messageList != null ? messageList.size() : 0;
    }

    // Common Interface or base fields for ViewHolders
    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;
        ImageView imgMessage;
        TextView tvTimestamp;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            imgMessage = itemView.findViewById(R.id.imgMessage);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }
    }

    static class SentMessageHolder extends MessageViewHolder {
        public SentMessageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class ReceivedMessageHolder extends MessageViewHolder {
        public ReceivedMessageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
