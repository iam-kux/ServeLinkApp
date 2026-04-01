package com.splinesoft.servelinkapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.models.User;
import com.splinesoft.servelinkapp.utils.Constants;
import com.splinesoft.servelinkapp.utils.FirebaseHelper;

public class SignupActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etPassword, etConfirmPassword;
    private RadioGroup rgRole;
    private MaterialButton btnSignup;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        rgRole = findViewById(R.id.rgRole);
        btnSignup = findViewById(R.id.btnSignup);
        tvLogin = findViewById(R.id.tvLogin);

        btnSignup.setOnClickListener(v -> createAccount());

        tvLogin.setOnClickListener(v -> finish());
    }

    private void createAccount() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        int selectedRoleId = rgRole.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRoleId);
        String role = selectedRadioButton.getText().toString(); // "Client" or "Service Provider"
        
        if (role.equals("Service Provider")) {
            role = Constants.ROLE_PROVIDER;
        } else {
            role = Constants.ROLE_CLIENT;
        }

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        btnSignup.setEnabled(false);
        btnSignup.setText("Creating Account...");

        String finalRole = role;
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = FirebaseHelper.getAuth().getCurrentUser().getUid();
                        User user = new User(userId, name, email, phone, finalRole);

                        FirebaseHelper.getFirestore().collection(Constants.USERS_REF)
                                .document(userId).set(user)
                                .addOnCompleteListener(dbTask -> {
                                    if (dbTask.isSuccessful()) {
                                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                        finishAffinity();
                                    } else {
                                        Toast.makeText(SignupActivity.this, "Failed to save user data.", Toast.LENGTH_SHORT).show();
                                        btnSignup.setEnabled(true);
                                        btnSignup.setText("Sign Up");
                                    }
                                });
                    } else {
                        Toast.makeText(SignupActivity.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        btnSignup.setEnabled(true);
                        btnSignup.setText("Sign Up");
                    }
                });
    }
}
