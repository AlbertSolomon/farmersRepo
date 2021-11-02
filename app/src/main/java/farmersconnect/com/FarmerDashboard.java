package farmersconnect.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class FarmerDashboard extends AppCompatActivity {
    CardView addProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_dashboard);

        addProduct = findViewById(R.id.addProduct);

        addProduct.setOnClickListener(view -> AddProduct());

    }

    public void AddProduct() {
        Intent intent = new Intent(FarmerDashboard.this,AddProduct.class);
        startActivity(intent);
    }

}