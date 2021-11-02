package farmersconnect.com;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.FirebaseApp;

public class FarmerCompanyRegister extends AppCompatActivity {

    CardView cardOfFarmer,cardOfCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_company_register);
        FirebaseApp.initializeApp(this);

        cardOfFarmer = findViewById(R.id.cardOfFarmer);
        cardOfCompany = findViewById(R.id.cardOfCompany);

        cardOfFarmer.setOnClickListener(view -> openFarmerRegisterPage());

        cardOfCompany.setOnClickListener(view -> openCompanyRegisterPage());
    }

    private void openFarmerRegisterPage() {
        Intent intent = new Intent(FarmerCompanyRegister.this,RegisteringFarmer.class);
        startActivity(intent);
    }

    private void openCompanyRegisterPage() {
        Intent intent = new Intent(FarmerCompanyRegister.this,RegisteringCompany.class);
        startActivity(intent);
    }
}