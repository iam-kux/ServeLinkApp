package com.splinesoft.servelinkapp.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.models.Service;
import com.splinesoft.servelinkapp.utils.Constants;
import com.splinesoft.servelinkapp.utils.FirebaseHelper;
import java.util.UUID;

public class AddServiceActivity extends AppCompatActivity {

    private EditText etTitle, etCategory, etDescription, etPrice, etLocation;
    private MaterialButton btnSave;
    private ImageView ivServiceImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        etTitle = findViewById(R.id.etTitle);
        etCategory = findViewById(R.id.etCategory);
        etDescription = findViewById(R.id.etDescription);
        etPrice = findViewById(R.id.etPrice);
        etLocation = findViewById(R.id.etLocation);
        btnSave = findViewById(R.id.btnSaveService);
        ivServiceImage = findViewById(R.id.ivServiceImage);

        btnSave.setOnClickListener(v -> saveService());
    }

    private void saveService() {
        String title = etTitle.getText().toString().trim();
        String category = etCategory.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();
        String location = etLocation.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(category) || 
            TextUtils.isEmpty(description) || TextUtils.isEmpty(priceStr) || 
            TextUtils.isEmpty(location)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        String serviceId = UUID.randomUUID().toString();
        String providerId = FirebaseHelper.getAuth().getCurrentUser() != null ? 
                FirebaseHelper.getAuth().getCurrentUser().getUid() : "dummy_provider";

        Service service = new Service(serviceId, providerId, title, description, category, price, location);

        btnSave.setEnabled(false);
        /*
        FirebaseHelper.getFirestore().collection(Constants.SERVICES_REF).document(serviceId)
                .set(service)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Service Added", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                        btnSave.setEnabled(true);
                    }
                });
        */
        Toast.makeText(this, "Service Added (Mock)", Toast.LENGTH_SHORT).show();
        finish();
    }
}
