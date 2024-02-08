package algonquin.CST2335.pate1214;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> cameraResultLauncher;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        // Get the email address passed from MainActivity
        String emailAddress = intent.getStringExtra("EmailAddress");

        // Assuming you have a TextView in your SecondActivity layout to display the email
        TextView emailTextView = findViewById(R.id.welcomeTextView); // You need to add this to your layout
        emailTextView.setText("Welcome back " + emailAddress);


        profileImage = findViewById(R.id.cameraImageView); // Ensure this ID matches your layout
        Button changePictureButton = findViewById(R.id.changePictureButton); // Ensure this ID matches your layout
        Button callButton = findViewById(R.id.callButton); // Ensure this ID matches your layout

        setupCameraResultLauncher();

        changePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraResultLauncher.launch(cameraIntent);
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText phoneEditText = findViewById(R.id.phoneEditText); // Ensure this ID matches your layout
                String phoneNumber = phoneEditText.getText().toString();
                if (!phoneNumber.isEmpty()) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(callIntent);
                } else {
                    Toast.makeText(SecondActivity.this, "Please enter a phone number.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupCameraResultLauncher() {
        cameraResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                            Intent data = result.getData();
                            Bitmap thumbnail = data.getParcelableExtra("data");
                            if (thumbnail != null) {
                                profileImage.setImageBitmap(thumbnail);
                            }
                        }
                    }
                });
    }
}