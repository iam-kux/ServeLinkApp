package com.splinesoft.servelinkapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.ui.auth.LoginActivity;
import com.splinesoft.servelinkapp.ui.common.SavedItemsActivity;
import com.splinesoft.servelinkapp.ui.common.SettingsActivity;
import com.splinesoft.servelinkapp.ui.common.WalletActivity;
import com.splinesoft.servelinkapp.ui.provider.EditProfileActivity;
import com.splinesoft.servelinkapp.ui.provider.PortfolioActivity;
import com.splinesoft.servelinkapp.utils.Constants;
import com.splinesoft.servelinkapp.utils.SessionManager;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private ImageView ivProfileImage;
    private TextView tvName, tvRole, tvEmail;
    private MaterialButton btnEditProfile, btnPortfolio, btnWallet, btnSavedItems, btnSettings, btnLogout;

    private SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = new SessionManager(requireContext());

        ivProfileImage = view.findViewById(R.id.ivProfileImage);
        tvName = view.findViewById(R.id.tvName);
        tvRole = view.findViewById(R.id.tvRole);
        tvEmail = view.findViewById(R.id.tvEmail);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        btnPortfolio = view.findViewById(R.id.btnPortfolio);
        btnWallet = view.findViewById(R.id.btnWallet);
        btnSavedItems = view.findViewById(R.id.btnSavedItems);
        btnSettings = view.findViewById(R.id.btnSettings);
        btnLogout = view.findViewById(R.id.btnLogout);

        setupNavigation();
        loadSessionData();
    }

    private void loadSessionData() {
        tvName.setText(sessionManager.getUserName());
        tvEmail.setText(sessionManager.getUserEmail());

        String role = sessionManager.getUserRole();
        if (Constants.ROLE_PROVIDER.equals(role)) {
            tvRole.setText("Service Provider");
            btnPortfolio.setVisibility(View.VISIBLE);
        } else if (Constants.ROLE_CLIENT.equals(role)) {
            tvRole.setText("Client / Employer");
            btnPortfolio.setVisibility(View.GONE);
        } else if (Constants.ROLE_ADMIN.equals(role)) {
            tvRole.setText("Administrator");
            btnPortfolio.setVisibility(View.GONE);
        } else {
            tvRole.setText(role);
            btnPortfolio.setVisibility(View.GONE);
        }

        String imageUrl = sessionManager.getUserImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.baseline_person_24)
                    .error(R.drawable.baseline_person_24)
                    .into(ivProfileImage);
        } else {
            ivProfileImage.setImageResource(R.drawable.baseline_person_24);
        }
    }

    private void setupNavigation() {
        btnEditProfile.setOnClickListener(v -> startActivity(new Intent(requireContext(), EditProfileActivity.class)));
        btnPortfolio.setOnClickListener(v -> startActivity(new Intent(requireContext(), PortfolioActivity.class)));
        btnWallet.setOnClickListener(v -> startActivity(new Intent(requireContext(), WalletActivity.class)));
        btnSavedItems.setOnClickListener(v -> startActivity(new Intent(requireContext(), SavedItemsActivity.class)));
        btnSettings.setOnClickListener(v -> startActivity(new Intent(requireContext(), SettingsActivity.class)));

        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            sessionManager.clearSession();
            Intent intent = new Intent(requireActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadSessionData();
    }
}
