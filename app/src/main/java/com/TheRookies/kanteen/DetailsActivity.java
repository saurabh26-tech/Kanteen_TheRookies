package com.TheRookies.kanteen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.hardware.ConsumerIrManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.TheRookies.kanteen.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

        public class DetailsActivity extends AppCompatActivity {
            Button editprofile;
            EditText name,email,academics;
            String userPhone;
            String currentUserID;
            CircleImageView profilephoto;
            FirebaseDatabase firebaseDatabase;
            StorageReference ref;
            FirebaseUser firebaseUser;
            DatabaseReference reference;
            Uri imageuri;
            int PICK_IMAGE_REQUEST=10;
            StorageTask mUploadTask;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_details);
                userPhone=getIntent().getStringExtra("mobileDetail");
                editprofile=findViewById(R.id.profile_edit);
                name=findViewById(R.id.profile_name);
                email=findViewById(R.id.profile_email);
                academics=findViewById(R.id.profile_academic);
                profilephoto=findViewById(R.id.profile_image);
                editprofile=findViewById(R.id.profile_edit);
                firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                currentUserID=firebaseUser.getUid();
                ref= FirebaseStorage.getInstance().getReference().child("images");
                reference=FirebaseDatabase.getInstance().getReference().child("Users");




                profilephoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openFileChooser();
                    }
                });

                editprofile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mUploadTask != null && mUploadTask.isInProgress()) {
                            Toast.makeText(DetailsActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                        } else {
                            uploadFile();
                        }

                    }});}

            @Override
            public void onBackPressed() {

                if(academics==null)
                {
                    Toast.makeText(this, "Please Fill The Details", Toast.LENGTH_SHORT).show();
                }
            }

            private void uploadFile() {


                        if (imageuri != null) {
                            final StorageReference fileReference = ref.child(System.currentTimeMillis()
                                    + "." + getFileExtension(imageuri));
                            mUploadTask = fileReference.putFile(imageuri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mProgressBar.setProgress(0);
//                                }
//                            }, 500);
                                            Toast.makeText(DetailsActivity.this, "Upload successful", Toast.LENGTH_LONG).show();


                                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    String Name = name.getText().toString();
                                                    String Phone = userPhone;
                                                    String Email = email.getText().toString();
                                                    String Academics = academics.getText().toString();
                                                    Users uploads = new Users(uri.toString(), Name, Phone, Email, Academics,currentUserID);
                                                    reference.child(firebaseUser.getUid()).setValue(uploads).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Intent i = new Intent(DetailsActivity.this, NavigationActivity.class);
                                                            startActivity(i);
                                                            finish();
                                                        }
                                                    });
                                                }
                                            });

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(DetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                            Toast.makeText(DetailsActivity.this, "uploading Wait", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(DetailsActivity.this, "No file selected", Toast.LENGTH_SHORT).show();
                        }


                    }

                    private String getFileExtension(Uri uri) {
                        ContentResolver cR = getContentResolver();
                        MimeTypeMap mime = MimeTypeMap.getSingleton();
                        return mime.getExtensionFromMimeType(cR.getType(uri));
                    }

                    public void openFileChooser() {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, PICK_IMAGE_REQUEST);
                    }


                    @Override
                    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                        super.onActivityResult(requestCode, resultCode, data);
                        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                                && data != null && data.getData() != null) {
                            imageuri = data.getData();
                            //  Picasso.with(this).load(imageuri).into(profilephoto);
                            Picasso.get().load(imageuri).into(profilephoto);
                        }
                    }}