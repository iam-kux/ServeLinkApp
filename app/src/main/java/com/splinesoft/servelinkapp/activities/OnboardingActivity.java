package com.splinesoft.servelinkapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.button.MaterialButton;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.adapters.OnboardingAdapter;
import com.splinesoft.servelinkapp.models.OnboardingItem;
import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutIndicators;
    private MaterialButton btnNext;
    private TextView btnSkip;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        layoutIndicators = findViewById(R.id.layoutIndicators);
        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);
        viewPager = findViewById(R.id.viewPager);

        setupOnboardingItems();

        viewPager.setAdapter(onboardingAdapter);
        setupIndicators();
        setCurrentIndicator(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);

                if (position == onboardingAdapter.getItemCount() - 1) {
                    btnNext.setText("Get Started");
                } else {
                    btnNext.setText("Next");
                }
            }
        });

        btnNext.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                finishOnboarding();
            }
        });

        btnSkip.setOnClickListener(v -> finishOnboarding());
    }

    private void setupOnboardingItems() {
        List<OnboardingItem> items = new ArrayList<>();
        items.add(new OnboardingItem(
                R.drawable.ima1,
                "Find trusted service providers near you",
                "Easily discover plumbers, electricians, mechanics, and more with just a few taps."
        ));
        items.add(new OnboardingItem(
                R.drawable.ima1,
                "Book services quickly and easily",
                "Select a convenient date and time. Get confirmation instantly from the best providers."
        ));
        items.add(new OnboardingItem(
                R.drawable.ima1,
                "Track jobs and chat with providers",
                "Stay updated on your booking status and chat securely with your provider in real-time."
        ));
        onboardingAdapter = new OnboardingAdapter(items);
    }

    private void setupIndicators() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentIndicator(int index) {
        int childCount = layoutIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.indicator_inactive
                ));
            }
        }
    }

    private void finishOnboarding() {
        SharedPreferences prefs = getSharedPreferences("ServeLinkPrefs", MODE_PRIVATE);
        prefs.edit().putBoolean("isFirstRun", false).apply();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
