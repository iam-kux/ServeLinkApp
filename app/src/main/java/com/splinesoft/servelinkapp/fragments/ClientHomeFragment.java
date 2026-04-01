package com.splinesoft.servelinkapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.adapters.CategoryAdapter;
import com.splinesoft.servelinkapp.adapters.ServiceAdapter;
import com.splinesoft.servelinkapp.models.Category;
import com.splinesoft.servelinkapp.models.Service;
import java.util.ArrayList;
import java.util.List;

public class ClientHomeFragment extends Fragment {

    private RecyclerView rvCategories, rvRecommended;
    private CategoryAdapter categoryAdapter;
    private ServiceAdapter serviceAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_client_home, container, false);

        rvCategories = root.findViewById(R.id.rvCategories);
        rvRecommended = root.findViewById(R.id.rvRecommended);

        setupCategories();
        setupRecommended();

        return root;
    }

    private void setupCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Plumber", R.mipmap.ic_launcher));
        categories.add(new Category("Electrician", R.mipmap.ic_launcher));
        categories.add(new Category("Cleaner", R.mipmap.ic_launcher));
        categories.add(new Category("Painter", R.mipmap.ic_launcher));
        categories.add(new Category("Carpenter", R.mipmap.ic_launcher));
        categories.add(new Category("Mechanic", R.mipmap.ic_launcher));

        categoryAdapter = new CategoryAdapter(categories, category -> {
            Toast.makeText(getContext(), "Selected: " + category.getName(), Toast.LENGTH_SHORT).show();
        });

        // 3 items per row
        rvCategories.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvCategories.setAdapter(categoryAdapter);
    }

    private void setupRecommended() {
        // Mock data
        List<Service> services = new ArrayList<>();
        services.add(new Service("1", "provider1", "Home Plumbing Repair", "Fixes leaks and pipes.", "Plumber", 50.0, "Harare"));
        services.add(new Service("2", "provider2", "Electrical Wiring", "Professional wiring and repair.", "Electrician", 80.0, "Byo"));
        services.add(new Service("3", "provider3", "Deep Cleaning", "Full house deep cleaning.", "Cleaner", 40.0, "Harare"));

        serviceAdapter = new ServiceAdapter(services, service -> {
            // Intent intent = new Intent(getContext(), ServiceDetailActivity.class);
            // intent.putExtra("serviceId", service.getServiceId());
            // startActivity(intent);
            Toast.makeText(getContext(), "Opening: " + service.getTitle(), Toast.LENGTH_SHORT).show();
        });

        rvRecommended.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRecommended.setAdapter(serviceAdapter);
    }
}
