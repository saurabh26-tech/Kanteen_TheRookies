package com.TheRookies.kanteen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.TheRookies.kanteen.Model.Dish;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodDetailsActivity extends AppCompatActivity {
    TextView Name,Price,Cuisine,Description;
    CircleImageView Image,Cart;
    Button Back,AddtoCart;
    DatabaseReference ProductRef;
    String dishId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        Name=findViewById(R.id.name);
        Price=findViewById(R.id.price_details);
        Cuisine=findViewById(R.id.cuisine);
        Description=findViewById(R.id.description);
        Image=findViewById(R.id.imageView5);
        dishId=getIntent().getStringExtra("DishId");
        ProductRef= FirebaseDatabase.getInstance().getReference().child("Dish");
}
}