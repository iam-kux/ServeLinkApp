package com.splinesoft.servelinkapp.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.models.Booking;
import com.splinesoft.servelinkapp.utils.Constants;
import com.splinesoft.servelinkapp.utils.FirebaseHelper;
import java.util.Calendar;
import java.util.UUID;

public class BookingActivity extends AppCompatActivity {

    private EditText etDate, etTime;
    private MaterialButton btnConfirm;
    private String serviceId, providerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        serviceId = getIntent().getStringExtra("serviceId");
        providerId = getIntent().getStringExtra("providerId");

        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        btnConfirm = findViewById(R.id.btnConfirmBooking);

        etDate.setOnClickListener(v -> showDatePicker());
        etTime.setOnClickListener(v -> showTimePicker());

        btnConfirm.setOnClickListener(v -> confirmBooking());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            etDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            String amPm = hourOfDay >= 12 ? "PM" : "AM";
            int hour = hourOfDay % 12 == 0 ? 12 : hourOfDay % 12;
            etTime.setText(String.format("%02d:%02d %s", hour, minute, amPm));
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        dialog.show();
    }

    private void confirmBooking() {
        String date = etDate.getText().toString();
        String time = etTime.getText().toString();

        if (TextUtils.isEmpty(date) || TextUtils.isEmpty(time)) {
            Toast.makeText(this, "Please select date and time", Toast.LENGTH_SHORT).show();
            return;
        }

        btnConfirm.setEnabled(false);
        String bookingId = UUID.randomUUID().toString();
        String clientId = FirebaseHelper.getAuth().getCurrentUser().getUid();

        Booking booking = new Booking(bookingId, clientId, providerId, serviceId, date, time, Constants.STATUS_PENDING);

        FirebaseHelper.getFirestore().collection(Constants.BOOKINGS_REF).document(bookingId)
                .set(booking)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Booking Confirmed!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Booking Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        btnConfirm.setEnabled(true);
                    }
                });
    }
}
