package com.splinesoft.servelinkapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.activities.AddServiceActivity;
import com.splinesoft.servelinkapp.adapters.ServiceAdapter;
import com.splinesoft.servelinkapp.models.Service;
import java.util.ArrayList;
import java.util.List;

public class ProviderServicesFragment extends Fragment {

    private RecyclerView rvMyServices;
    private FloatingActionButton fabAddService;
    private ServiceAdapter serviceAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_provider_services, container, false);

        rvMyServices = root.findViewById(R.id.rvMyServices);
        fabAddService = root.findViewById(R.id.fabAddService);

        fabAddService.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddServiceActivity.class));
        });

        // Mock data
        List<Service> services = new ArrayList<>();
        services.add(new Service("1", "providerId", "Home Plumbing Repair", "Fixing leaks", "Plumber", 50.0, "Harare"));

        serviceAdapter = new ServiceAdapter(services, service -> {
            // Can edit service here
        });
        rvMyServices.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMyServices.setAdapter(serviceAdapter);

        return root;
    }
}
