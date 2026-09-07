package com.splinesoft.servelinkapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.adapters.BookingAdapter;
import com.splinesoft.servelinkapp.models.Booking;
import com.splinesoft.servelinkapp.ui.provider.viewmodel.ProviderDashboardViewModel;
import com.splinesoft.servelinkapp.utils.Constants;

import java.util.ArrayList;
import java.util.Locale;

public class ProviderDashboardFragment extends Fragment {

    private TextView tvTotalJobs;
    private TextView tvEarnings;
    private RecyclerView rvProviderBookings;

    private ProviderDashboardViewModel viewModel;
    private BookingAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_dashboard, container, false);

        tvTotalJobs = view.findViewById(R.id.tvTotalJobs);
        tvEarnings = view.findViewById(R.id.tvEarnings);
        rvProviderBookings = view.findViewById(R.id.rvProviderBookings);

        rvProviderBookings.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ProviderDashboardViewModel.class);
        observeViewModel();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadDashboardData();
    }

    private void observeViewModel() {
        viewModel.getTotalJobs().observe(getViewLifecycleOwner(), total -> 
                tvTotalJobs.setText(String.valueOf(total)));

        viewModel.getEarnings().observe(getViewLifecycleOwner(), amount -> 
                tvEarnings.setText(String.format(Locale.getDefault(), "$%.0f", amount)));

        viewModel.getBookings().observe(getViewLifecycleOwner(), list -> {
            if (list == null) list = new ArrayList<>();
            adapter = new BookingAdapter(list, true, new BookingAdapter.OnBookingActionListener() {
                @Override
                public void onAccept(Booking booking) {
                    viewModel.updateBookingStatus(booking, Constants.BOOKING_STATUS_ACCEPTED);
                }

                @Override
                public void onDecline(Booking booking) {
                    viewModel.updateBookingStatus(booking, Constants.BOOKING_STATUS_CANCELLED);
                }

                @Override
                public void onItemClick(Booking booking) {
                    try {
                        Intent intent = new Intent(getContext(), 
                                Class.forName("com.splinesoft.servelinkapp.ui.common.BookingActivity"));
                        intent.putExtra(Constants.EXTRA_BOOKING_ID, booking.getBookingId());
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        Toast.makeText(getContext(), "Booking details screen not found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            rvProviderBookings.setAdapter(adapter);
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), msg -> {
            if (msg != null && !msg.isEmpty()) {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
