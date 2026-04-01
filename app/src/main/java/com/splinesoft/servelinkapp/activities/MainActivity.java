package com.splinesoft.servelinkapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.splinesoft.servelinkapp.utils.Constants;
import com.splinesoft.servelinkapp.utils.FirebaseHelper;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SharedPreferences prefs = getSharedPreferences("ServeLinkPrefs", MODE_PRIVATE);
        boolean isFirstRun = prefs.getBoolean("isFirstRun", true);

        if (isFirstRun) {
            startActivity(new Intent(this, OnboardingActivity.class));
            finish();
            return;
        }

        if (FirebaseHelper.getAuth().getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        
        String userId = FirebaseHelper.getAuth().getCurrentUser().getUid();
        FirebaseHelper.getFirestore().collection(Constants.USERS_REF).document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String role = documentSnapshot.getString("role");
                        if (Constants.ROLE_PROVIDER.equals(role)) {
                            startActivity(new Intent(MainActivity.this, ProviderMainActivity.class));
                        } else {
                            startActivity(new Intent(MainActivity.this, ClientMainActivity.class));
                        }
                        finish();
                    } else {
                        FirebaseHelper.getAuth().signOut();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
