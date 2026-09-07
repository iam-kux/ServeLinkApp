package com.splinesoft.servelinkapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.adapters.BookingAdapter;
import com.splinesoft.servelinkapp.models.Booking;
import com.splinesoft.servelinkapp.ui.client.viewmodel.ClientBookingsViewModel;
import com.splinesoft.servelinkapp.utils.Constants;

import java.util.ArrayList;

public class ClientBookingsFragment extends Fragment {

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rvBookings;
    private LinearLayout emptyState;

    private ClientBookingsViewModel viewModel;
    private BookingAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_bookings, container, false);

        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        rvBookings = view.findViewById(R.id.rvBookings);
        emptyState = view.findViewById(R.id.emptyState);

        rvBookings.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefresh.setOnRefreshListener(() -> viewModel.loadBookings());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ClientBookingsViewModel.class);
        observeViewModel();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadBookings();
    }

    private void observeViewModel() {
        viewModel.getBookings().observe(getViewLifecycleOwner(), list -> {
            swipeRefresh.setRefreshing(false);
            if (list == null || list.isEmpty()) {
                emptyState.setVisibility(View.VISIBLE);
                rvBookings.setVisibility(View.GONE);
            } else {
                emptyState.setVisibility(View.GONE);
                rvBookings.setVisibility(View.VISIBLE);

                adapter = new BookingAdapter(list, false, new BookingAdapter.OnBookingActionListener() {
                    @Override
                    public void onAccept(Booking booking) {}

                    @Override
                    public void onDecline(Booking booking) {}

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
                rvBookings.setAdapter(adapter);
            }
        });

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), loading -> {
            if (loading != null) {
                swipeRefresh.setRefreshing(loading);
            }
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), msg -> {
            if (msg != null && !msg.isEmpty()) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
