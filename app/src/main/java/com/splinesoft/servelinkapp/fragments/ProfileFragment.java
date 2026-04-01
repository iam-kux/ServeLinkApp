package com.splinesoft.servelinkapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.activities.LoginActivity;
import com.splinesoft.servelinkapp.models.User;
import com.splinesoft.servelinkapp.utils.Constants;
import com.splinesoft.servelinkapp.utils.FirebaseHelper;

public class ProfileFragment extends Fragment {

    private ImageView ivProfileImage;
    private TextView tvName, tvRole, tvEmail, tvPhone;
    private MaterialButton btnLogout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        ivProfileImage = root.findViewById(R.id.ivProfileImage);
        tvName = root.findViewById(R.id.tvName);
        tvRole = root.findViewById(R.id.tvRole);
        tvEmail = root.findViewById(R.id.tvEmail);
        tvPhone = root.findViewById(R.id.tvPhone);
        btnLogout = root.findViewById(R.id.btnLogout);

        loadUserData();

        btnLogout.setOnClickListener(v -> {
            FirebaseHelper.getAuth().signOut();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        return root;
    }

    private void loadUserData() {
        if (FirebaseHelper.getAuth().getCurrentUser() != null) {
            String userId = FirebaseHelper.getAuth().getCurrentUser().getUid();
            FirebaseHelper.getFirestore().collection(Constants.USERS_REF).document(userId)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            User user = documentSnapshot.toObject(User.class);
                            if (user != null) {
                                tvName.setText(user.getName());
                                tvRole.setText(user.getRole());
                                tvEmail.setText(user.getEmail());
                                tvPhone.setText(user.getPhone());
                                if (user.getProfileImage() != null && !user.getProfileImage().isEmpty()) {
                                    Glide.with(this).load(user.getProfileImage()).into(ivProfileImage);
                                }
                            }
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Error loading profile: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }
}
