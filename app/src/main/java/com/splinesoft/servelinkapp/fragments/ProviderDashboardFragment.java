package com.splinesoft.servelinkapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.adapters.BookingAdapter;
import com.splinesoft.servelinkapp.models.Booking;
import com.splinesoft.servelinkapp.utils.Constants;
import com.splinesoft.servelinkapp.utils.FirebaseHelper;
import java.util.ArrayList;
import java.util.List;

public class ProviderDashboardFragment extends Fragment {

    private RecyclerView rvProviderBookings;
    private TextView tvTotalJobs, tvEarnings;
    private BookingAdapter bookingAdapter;
    private List<Booking> bookingList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_provider_dashboard, container, false);

        tvTotalJobs = root.findViewById(R.id.tvTotalJobs);
        tvEarnings = root.findViewById(R.id.tvEarnings);
        rvProviderBookings = root.findViewById(R.id.rvProviderBookings);

        bookingList = new ArrayList<>();
        // Mock data
        bookingList.add(new Booking("1", "client1", "provider1", "service1", "12/12/2026", "10:00 AM", Constants.STATUS_PENDING));
        bookingList.add(new Booking("2", "client2", "provider1", "service2", "14/12/2026", "02:00 PM", Constants.STATUS_ACCEPTED));

        bookingAdapter = new BookingAdapter(bookingList, true, new BookingAdapter.OnBookingActionListener() {
            @Override
            public void onAccept(Booking booking) {
                updateBookingStatus(booking, Constants.STATUS_ACCEPTED);
            }

            @Override
            public void onDecline(Booking booking) {
                updateBookingStatus(booking, Constants.STATUS_DECLINED);
            }
        });

        rvProviderBookings.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProviderBookings.setAdapter(bookingAdapter);

        tvTotalJobs.setText("12");
        tvEarnings.setText("$450");

        return root;
    }

    private void updateBookingStatus(Booking booking, String status) {
        booking.setStatus(status);
        bookingAdapter.notifyDataSetChanged();
        
        // Disable real firestore update to avoid crashing the mockup
        /*
        FirebaseHelper.getFirestore().collection(Constants.BOOKINGS_REF).document(booking.getBookingId())
                .update("status", status)
                .addOnSuccessListener(aVoid -> Toast.makeText(getContext(), "Booking " + status, Toast.LENGTH_SHORT).show());
        */
        Toast.makeText(getContext(), "Booking " + status, Toast.LENGTH_SHORT).show();
    }
}
