package ui;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import algonquin.CST2335.pate1214.R;

public class MainActivity extends AppCompatActivity {

    // Declare class variables
    private EditText passwordEditText;
    private Button loginButton;

    private TextView passwordTextV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load views using findViewById
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        passwordTextV = findViewById(R.id.passwordTextView);

        // Add OnClickListener to the loginButton
        loginButton.setOnClickListener(view -> {
            // Read password string from EditText
            String password = passwordEditText.getText().toString();

            // Check password complexity
            boolean isComplex = checkPasswordComplexity(password);

            // Update TextView based on password complexity result
            if (isComplex) {
                Toast.makeText(MainActivity.this, "Your password meets the requirements", Toast.LENGTH_SHORT).show();
                passwordTextV.setText("Your password meets the requirements");
            } else {
                Toast.makeText(MainActivity.this, "You shall not pass!", Toast.LENGTH_SHORT).show();
                passwordTextV.setText("You shall not pass!");
            }
        });
    }

    /**
     * Check if the password meets complexity requirements.
     *
     * @param password The password string to be checked.
     * @return True if the password meets complexity requirements, false otherwise.
     */
    private boolean checkPasswordComplexity(String password) {
        boolean foundUpperCase = false;
        boolean foundLowerCase = false;
        boolean foundNumber = false;
        boolean foundSpecial = false;

        // Iterate over each character in the password string
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c)) {
                foundSpecial = true;
            }
        }

        // Check if any of the requirements are missing and display Toast messages
        if (!foundUpperCase) {
            Toast.makeText(this, "Your password does not have an upper case letter", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundLowerCase) {
            Toast.makeText(this, "Your password does not have a lower case letter", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundNumber) {
            Toast.makeText(this, "Your password does not have a number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundSpecial) {
            Toast.makeText(this, "Your password does not have a special symbol", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true; // Password meets complexity requirements
        }
    }

    /**
     * Check if the character is a special character.
     *
     * @param c The character to be checked.
     * @return True if the character is a special character, false otherwise.
     */
    private boolean isSpecialCharacter(char c) {
        switch (c) {
            case '#':
            case '$':
            case '%':
            case '^':
            case '&':
            case '*':
            case '!':
            case '@':
            case '?':
                return true;
            default:
                return false;
        }
    }
}