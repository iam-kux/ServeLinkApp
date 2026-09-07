package com.splinesoft.servelinkapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.models.Booking;
import com.splinesoft.servelinkapp.utils.Constants;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private final List<Booking> bookingList;
    private final boolean isProvider;
    private final OnBookingActionListener listener;

    public interface OnBookingActionListener {
        void onAccept(Booking booking);
        void onDecline(Booking booking);
        void onItemClick(Booking booking);
    }

    public BookingAdapter(List<Booking> bookingList, boolean isProvider, OnBookingActionListener listener) {
        this.bookingList = bookingList;
        this.isProvider = isProvider;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking_card, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);

        holder.tvServiceTitle.setText(booking.getServiceTitle() != null ? booking.getServiceTitle() : "On-Demand Service");
        holder.tvClientName.setText(isProvider 
                ? "Client: " + (booking.getClientName() != null ? booking.getClientName() : "User")
                : "Provider: " + (booking.getProviderName() != null ? booking.getProviderName() : "Freelancer"));
        
        holder.tvDate.setText("Date: " + booking.getDate());
        holder.tvTime.setText("Time: " + booking.getTime());
        holder.tvStatus.setText(booking.getStatus().toUpperCase());

        // Highlight status colors
        String status = booking.getStatus().toLowerCase();
        if (Constants.BOOKING_STATUS_PENDING.equals(status)) {
            holder.tvStatus.setTextColor(holder.itemView.getContext().getColor(android.R.color.holo_orange_dark));
            if (isProvider) {
                holder.layoutActionButtons.setVisibility(View.VISIBLE);
            } else {
                holder.layoutActionButtons.setVisibility(View.GONE);
            }
        } else {
            holder.layoutActionButtons.setVisibility(View.GONE);
            if (Constants.BOOKING_STATUS_COMPLETED.equals(status)) {
                holder.tvStatus.setTextColor(holder.itemView.getContext().getColor(android.R.color.holo_green_dark));
            } else if (Constants.BOOKING_STATUS_CANCELLED.equals(status)) {
                holder.tvStatus.setTextColor(holder.itemView.getContext().getColor(android.R.color.holo_red_dark));
            } else {
                holder.tvStatus.setTextColor(holder.itemView.getContext().getColor(android.R.color.holo_blue_dark));
            }
        }

        holder.btnAccept.setOnClickListener(v -> {
            if (listener != null) listener.onAccept(booking);
        });

        holder.btnDecline.setOnClickListener(v -> {
            if (listener != null) listener.onDecline(booking);
        });

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(booking);
        });
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView tvServiceTitle, tvStatus, tvClientName, tvDate, tvTime;
        LinearLayout layoutActionButtons;
        MaterialButton btnAccept, btnDecline;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceTitle = itemView.findViewById(R.id.tvServiceTitle);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvClientName = itemView.findViewById(R.id.tvClientName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            layoutActionButtons = itemView.findViewById(R.id.layoutActionButtons);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnDecline = itemView.findViewById(R.id.btnDecline);
        }
    }
}
