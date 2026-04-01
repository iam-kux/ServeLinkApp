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

    private List<Booking> bookingList;
    private boolean isProvider;
    private OnBookingActionListener listener;

    public interface OnBookingActionListener {
        void onAccept(Booking booking);
        void onDecline(Booking booking);
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

        holder.tvServiceTitle.setText("Service: " + booking.getServiceId());
        holder.tvClientName.setText(isProvider ? "Client ID: " + booking.getClientId() : "Provider ID: " + booking.getProviderId());
        holder.tvDate.setText("Date: " + booking.getDate());
        holder.tvTime.setText("Time: " + booking.getTime());
        holder.tvStatus.setText(booking.getStatus());

        if (isProvider && Constants.STATUS_PENDING.equals(booking.getStatus())) {
            holder.layoutActionButtons.setVisibility(View.VISIBLE);
        } else {
            holder.layoutActionButtons.setVisibility(View.GONE);
        }

        holder.btnAccept.setOnClickListener(v -> {
            if (listener != null) listener.onAccept(booking);
        });

        holder.btnDecline.setOnClickListener(v -> {
            if (listener != null) listener.onDecline(booking);
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
