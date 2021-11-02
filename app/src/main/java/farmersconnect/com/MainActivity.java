package farmersconnect.com;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button loginButton, signupButton;
    EditText PasswordText, userEmail;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton= findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);
        userEmail= findViewById(R.id.userEmail);
        PasswordText= findViewById(R.id.PasswordText);

        mFirebaseAuth = FirebaseAuth.getInstance();
        //FirebaseAuth.AuthStateListener myAuthListener;


        loginButton.setOnClickListener(view -> {
            String username = userEmail.getText().toString();
            String password = PasswordText.getText().toString();

            if (username.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please enter your email", Toast.LENGTH_SHORT).show();
            }
            else if (password.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please enter your Password", Toast.LENGTH_SHORT).show();
            }
            else if(password.length() < 5){
                Toast.makeText(getApplicationContext(),"Your password should contain at least 5 characters", Toast.LENGTH_SHORT).show();
            }
            else {
                openNextActivity(username, password);
            }
        });


        signupButton.setOnClickListener(view -> signingUp());
    }

    public void openNextActivity(String username, String password){

        mFirebaseAuth.signInWithEmailAndPassword(username,password).addOnSuccessListener(authResult -> {
            startActivity(new Intent(MainActivity.this, FarmerCompanyRegister.class));
            finish();
            Toast.makeText(MainActivity.this, "Login successful!",Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Login Failed because of a network issue!",Toast.LENGTH_SHORT).show());

    }

    public void signingUp(){
        Intent intent = new Intent(MainActivity.this,signUp.class);
        startActivity(intent);
    }
}