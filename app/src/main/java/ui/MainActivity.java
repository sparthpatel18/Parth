package ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import algonquin.CST2335.pate1214.R;
import algonquin.CST2335.pate1214.databinding.ActivityMainBinding;
import data.MainViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(MainViewModel.class);

        // Set up click listener for the button
        binding.mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editString = binding.myedittext.getText().toString();
                binding.textview.setText("Your edit text has: " + editString);
                model.setEditTextValue(editString);
            }
        });

        // Observe the LiveData and update the UI components accordingly
        model.setCheckboxState().observe(this, isChecked -> {
            if (isChecked != null) {
                binding.checkbox.setChecked(isChecked);
                showToast("Checkbox state: " + isChecked);
            }
        });

        model.setSwitchState().observe(this, isChecked -> {
            if (isChecked != null) {
                binding.switchBox.setChecked(isChecked);
                showToast("Switch state: " + isChecked);
            }
        });

        model.setRadioButtonState().observe(this, checkedId -> {
            if (checkedId != null) {
                // Check if the checkedId matches the RadioButton's ID
                binding.radioButton.setChecked(checkedId == R.id.radioButton);
                showToast("Radio button state: " + (checkedId == R.id.radioButton));
            }
        });

        // Set listeners for CompoundButtons using Lambda expressions
        binding.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> model.setCheckboxState().postValue(isChecked));
        binding.switchBox.setOnCheckedChangeListener((buttonView, isChecked) -> model.setSwitchState().postValue(isChecked));
        binding.radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Post the ID of the RadioButton when it is checked
                model.setRadioButtonState().postValue(R.id.radioButton);
            }
        });

        // Set up click listener for ImageButton
        binding.myimagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = binding.myimagebutton.getWidth();
                int height = binding.myimagebutton.getHeight();
                showToast("The width = " + width + " and height = " + height);
            }
        });

        // Load an image into the ImageView
        binding.myimageview.setImageResource(R.drawable.algonquin);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}