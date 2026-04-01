package com.splinesoft.servelinkapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.models.Service;
import com.splinesoft.servelinkapp.utils.Constants;
import com.splinesoft.servelinkapp.utils.FirebaseHelper;

public class ServiceDetailActivity extends AppCompatActivity {

    private ImageView ivServiceImage;
    private TextView tvTitle, tvRating, tvLocation, tvPrice, tvDescription;
    private MaterialButton btnMessage, btnBook;
    private String serviceId, providerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        ivServiceImage = findViewById(R.id.ivServiceImage);
        tvTitle = findViewById(R.id.tvTitle);
        tvRating = findViewById(R.id.tvRating);
        tvLocation = findViewById(R.id.tvLocation);
        tvPrice = findViewById(R.id.tvPrice);
        tvDescription = findViewById(R.id.tvDescription);
        btnMessage = findViewById(R.id.btnMessage);
        btnBook = findViewById(R.id.btnBook);

        serviceId = getIntent().getStringExtra("serviceId");

        if (serviceId != null) {
            loadServiceDetails();
        }

        btnBook.setOnClickListener(v -> {
            Intent intent = new Intent(ServiceDetailActivity.this, BookingActivity.class);
            intent.putExtra("serviceId", serviceId);
            intent.putExtra("providerId", providerId);
            startActivity(intent);
        });

        btnMessage.setOnClickListener(v -> {
             Intent intent = new Intent(ServiceDetailActivity.this, ChatActivity.class);
             intent.putExtra("receiverId", providerId);
             startActivity(intent);
        });
    }

    private void loadServiceDetails() {
        FirebaseHelper.getFirestore().collection(Constants.SERVICES_REF).document(serviceId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Service service = documentSnapshot.toObject(Service.class);
                        if (service != null) {
                            providerId = service.getProviderId();
                            tvTitle.setText(service.getTitle());
                            tvRating.setText(String.valueOf(service.getRating()));
                            tvLocation.setText(service.getLocation());
                            tvPrice.setText("$" + service.getPrice());
                            tvDescription.setText(service.getDescription());

                            if (service.getImage() != null && !service.getImage().isEmpty()) {
                                Glide.with(this).load(service.getImage()).into(ivServiceImage);
                            }
                        }
                    } else {
                        // For dummy mock data if Firestore is empty
                        tvTitle.setText("Service " + serviceId);
                        tvPrice.setText("$50");
                        tvDescription.setText("Sample Description.");
                    }
                });
    }
}
