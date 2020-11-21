package com.TheRookies.kanteen.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.TheRookies.kanteen.LoginActivity;
import com.TheRookies.kanteen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import static android.icu.text.DisplayContext.LENGTH_SHORT;


public class account extends Fragment {
    FirebaseUser mUser;
    DatabaseReference mUserref;

    TextView Name ,Email ,Phone ,acadamic;
    Button edit_profile , logout;
    ImageButton back;
    CircularImageView profile_image;



    String imageurl,name,phone,email,academic,uid;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_account, container, false);

        mUser=FirebaseAuth.getInstance().getCurrentUser();
        mUserref= FirebaseDatabase.getInstance().getReference();


        Name= view.findViewById(R.id.display_profile_Name);
        Email= view.findViewById(R.id.display_profile_Email);
        Phone= view.findViewById(R.id.display_profile_Phone);
        acadamic= view.findViewById(R.id.display_profile_Acadamics);
        edit_profile= view.findViewById(R.id.edit);
        logout= view.findViewById(R.id.logout);
        back= view.findViewById(R.id.back);
        profile_image= view.findViewById(R.id.profile_photo);


        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));

            }
        });
        mUserref.child("Users").child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {


                    email= snapshot.child("email").getValue().toString();
                    phone= snapshot.child("phone").getValue().toString();
                    academic= snapshot.child("academic").getValue().toString();
                    name = snapshot.child("name").getValue().toString();
                    uid = snapshot.child("uid").getValue().toString();
                    imageurl = snapshot.child("imageurl").getValue().toString();

                    Picasso.get().load(imageurl).into(profile_image);
                    Name.setText(name);
                    Email.setText(email);
                    Phone.setText(phone);
                    acadamic.setText(academic);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getActivity(),"Add profile please",Toast.LENGTH_SHORT).show();
            }
        });

        return  view;
    }



      }
