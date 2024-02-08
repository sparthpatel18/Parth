package ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import algonquin.CST2335.pate1214.R;
import algonquin.CST2335.pate1214.SecondActivity;
import algonquin.CST2335.pate1214.databinding.ActivityMainBinding;
import data.MainViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private TextView mytext;
    private Button mybutton;
    private EditText myedittext;
    private CheckBox checkBox;
    private Switch switchButton;
    private RadioButton radioButton;
    private ActivityMainBinding variableBinding;
    private MainViewModel model;
    private ImageView myImageView;
    private ImageButton myImageButton;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );



        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);
        setContentView(R.layout.activity_main);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());
        Button loginButton = findViewById(R.id.loginButton);

        // Set an OnClickListener on the login button

        final EditText emailEditText = findViewById(R.id.emailEditText);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start SecondActivity
                Intent nextPageIntent = new Intent(MainActivity.this, SecondActivity.class);

                // Attach the email address to the Intent
                nextPageIntent.putExtra("EmailAddress", emailEditText.getText().toString());

                // Start the activity using the intent
                startActivity(nextPageIntent);

            }
        });

    }

    @Override

    protected void onResume() {

        super.onResume();
        Log.w(TAG, "The activity has become visible (it is now \"resumed\"). This is where you start animations, acquire exclusive resources, etc.");
    }

    @Override
    protected void onStart() {
        Log.w(TAG, "The activity is about to become visible. This is a good place to check resources the activity needs.");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.w(TAG, "Another activity is taking focus (this activity is about to be \"paused\"). This is where you should stop animations, release exclusive resources, etc.");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.w(TAG, "The activity is about to be destroyed. This is the final call where you can clean up resources, including thread, listeners, and receivers.");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.w(TAG, "The activity is no longer visible (it is now \"stopped\"). This is where you should release resources that might leak memory.");
        super.onStop();


    }
}