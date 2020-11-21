package com.TheRookies.kanteen;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;
public class LoginActivity extends AppCompatActivity {

    EditText inputmobile;
    Button getotp ;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    //public static FragmentManager fragmentManager;
    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!= null){
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getotp=findViewById(R.id.get_otp);
        inputmobile=findViewById(R.id.ph_number);
        progressBar=findViewById(R.id.progressbar);
        firebaseAuth=FirebaseAuth.getInstance();

        //    fragmentManager=getSupportFragmentManager();
        String num=inputmobile.getText().toString();

//     Bundle bundle =new Bundle();
//
//
//     bundle.putString("number",num);
//        workfragment workfragment =new workfragment();
//        workfragment.setArguments(bundle);



        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputmobile.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Enter correct Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    getotp.setVisibility(View.VISIBLE);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+inputmobile.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            LoginActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(verificationId, forceResendingToken);
                                    progressBar.setVisibility(View.GONE );
                                    Intent i =new Intent(LoginActivity.this,verifyotp.class);
                                    i.putExtra("mobile",inputmobile.getText().toString());
                                    i.putExtra("verificationid",verificationId);
                                    startActivity(i);
                                }
                                @Override
                                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.GONE);
                                    getotp.setVisibility(View.VISIBLE);
                                }
                                @Override
                                public void onVerificationFailed(FirebaseException e) {
                                    progressBar.setVisibility(View.GONE);
                                    getotp.setVisibility(View.VISIBLE);
                                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}