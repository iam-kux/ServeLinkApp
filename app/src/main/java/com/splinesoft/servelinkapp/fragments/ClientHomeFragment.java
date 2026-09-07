package com.splinesoft.servelinkapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.adapters.CategoryAdapter;
import com.splinesoft.servelinkapp.adapters.ProviderAdapter;
import com.splinesoft.servelinkapp.ui.client.ProviderDetailActivity;
import com.splinesoft.servelinkapp.ui.client.viewmodel.ClientHomeViewModel;
import com.splinesoft.servelinkapp.ui.common.SearchActivity;
import com.splinesoft.servelinkapp.utils.Constants;

import java.util.ArrayList;

public class ClientHomeFragment extends Fragment {

    private EditText etSearch;
    private RecyclerView rvCategories, rvRecommended;
    private CategoryAdapter categoryAdapter;
    private ProviderAdapter providerAdapter;
    private ClientHomeViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_home, container, false);

        etSearch = view.findViewById(R.id.etSearch);
        rvCategories = view.findViewById(R.id.rvCategories);
        rvRecommended = view.findViewById(R.id.rvRecommended);

        // Search redirection
        etSearch.setFocusable(false);
        etSearch.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });

        setupRecyclerViews();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ClientHomeViewModel.class);
        observeViewModel();

        viewModel.loadData();
    }

    private void setupRecyclerViews() {
        rvCategories.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvRecommended.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void observeViewModel() {
        viewModel.getCategories().observe(getViewLifecycleOwner(), list -> {
            categoryAdapter = new CategoryAdapter(list, category -> {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("query", category.getName());
                startActivity(intent);
            });
            rvCategories.setAdapter(categoryAdapter);
        });

        viewModel.getRecommendedProviders().observe(getViewLifecycleOwner(), list -> {
            providerAdapter = new ProviderAdapter(list, provider -> {
                Intent intent = new Intent(getContext(), ProviderDetailActivity.class);
                intent.putExtra(Constants.EXTRA_PROVIDER_ID, provider.getUserId());
                startActivity(intent);
            });
            rvRecommended.setAdapter(providerAdapter);
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), msg -> {
            if (msg != null && !msg.isEmpty()) {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
