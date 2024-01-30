package ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import algonquin.CST2335.pate1214.R;
import algonquin.CST2335.pate1214.databinding.ActivityMainBinding;
import data.MainViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel model;

    // Declare variables for ImageView and Switch
    private ImageView imgView;
    private Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Find ImageView and Switch by their IDs
        imgView = findViewById(R.id.flagview);
        sw = findViewById(R.id.switch1);

        // Set an OnCheckedChangeListener for the Switch
        sw.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Start animation if the switch is checked
                RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(5000);
                rotate.setRepeatCount(Animation.INFINITE);
                rotate.setInterpolator(new LinearInterpolator());
                imgView.startAnimation(rotate);
            } else {
                // Clear animation if the switch is unchecked
                imgView.clearAnimation();
            }
        });
    }
}