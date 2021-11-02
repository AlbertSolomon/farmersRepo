package farmersconnect.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class RegisteringFarmer extends AppCompatActivity {

    EditText NameOfFarmer,contactOfFarmer,residentAreaOfFarmer;
    Button button,choose_image;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    StorageReference profilePicOfFarmer;

    String collectionName = "FarmersCollection";
    String imageURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registering_farmer);
        FirebaseApp.initializeApp(this);

        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        String currentUser;
        {
            currentUser = mFirebaseAuth.getCurrentUser().getUid();
        }

        NameOfFarmer = findViewById(R.id.NameOfFarmer);
        contactOfFarmer = findViewById(R.id.contactOfFarmer);
        residentAreaOfFarmer = findViewById(R.id.residentAreaOfFarmer);
        button = findViewById(R.id.button);
        choose_image = findViewById(R.id.choose_image);

        String nameOfFarmer,ContactOfFarmer,ResidentOfFarmer;
        {
            nameOfFarmer = NameOfFarmer.getText().toString();
            ContactOfFarmer = contactOfFarmer.getText().toString();
            ResidentOfFarmer = residentAreaOfFarmer.getText().toString();
        }

        button.setOnClickListener(view -> {
            Map<String, Object> farmerInfo = new HashMap<>();
            farmerInfo.put("NameOfFarmer",nameOfFarmer);
            farmerInfo.put("Contacts",ContactOfFarmer);
            farmerInfo.put("FarmersResident",ResidentOfFarmer);
            farmerInfo.put("PictureURL",imageURL);

            db.collection(collectionName).document(currentUser).set(farmerInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(getApplicationContext(), "Registered Successfully!!!", Toast.LENGTH_SHORT).show();
                    farmersDashboard();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Registration Failed!!!", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void farmersDashboard() {
        Intent intent = new Intent(RegisteringFarmer.this,FarmerDashboard.class);
        startActivity(intent);
        finish();
    }
}