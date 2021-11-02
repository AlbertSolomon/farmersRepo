package farmersconnect.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AddProduct extends AppCompatActivity {
    ImageView imageOfProduct;
    EditText productName,stock,amountPerKg;
    Button addProductBtn;
    String productImageUrl,collectionName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        collectionName = "Products";
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth myFirebaseAuth = FirebaseAuth.getInstance();

        String currentUser = myFirebaseAuth.getCurrentUser().getUid();

        imageOfProduct = findViewById(R.id.imageOfProduct);
        productName = findViewById(R.id.productName);
        stock = findViewById(R.id.stock);
        amountPerKg = findViewById(R.id.amountPerKg);
        addProductBtn = findViewById(R.id.addProductBtn);

        //Integer pricePerKg,myStock;
        String nameOfProduct,pricePerKg,myStock;
        {
            nameOfProduct = productName.getText().toString();
            pricePerKg = amountPerKg.getText().toString();
            myStock = stock.getText().toString();
        }

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> addingProduct = new HashMap<>();
                addingProduct.put("UserID", currentUser);
                addingProduct.put("NameOfProduct",nameOfProduct);
                addingProduct.put("Stock",myStock);
                addingProduct.put("Amount",pricePerKg);

                db.collection(collectionName).document(nameOfProduct).set(addingProduct).addOnSuccessListener(new OnSuccessListener<Void>() {
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
            }
        });

    }

    public void farmersDashboard(){
        //Intent intent = new Intent(AddProduct.this,Livingstonia.class);
        //startActivity(intent);
    }

}