package com.TheRookies.kanteen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class verifyotp extends AppCompatActivity {



    EditText inputcode1,inputcode2,inputcode3,inputcode4,inputcode5,inputcode6;
    ProgressBar progressBar;
    Button buttonverify;
    String verificationId;
    TextView resendcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verifyotp);
        progressBar=findViewById(R.id.progbar);
        buttonverify=findViewById(R.id.verify_otp);



        TextView textmobile;
        resendcode=findViewById(R.id.resend_otp);
        inputcode1=findViewById(R.id.inputcode1);
        inputcode2=findViewById(R.id.inputcode2);
        inputcode3=findViewById(R.id.inputcode3);
        inputcode4=findViewById(R.id.inputcode4);
        inputcode5=findViewById(R.id.inputcode5);
        inputcode6=findViewById(R.id.inputcode6);
        textmobile=findViewById(R.id.mobilenum);
        textmobile.setText(String.format("+91-%s",getIntent().getStringExtra("mobile")));


        setupotpinputs();



        verificationId=getIntent().getStringExtra("verificationid");



        buttonverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(inputcode1.getText().toString().trim().isEmpty()||
                        inputcode2.getText().toString().trim().isEmpty()||
                        inputcode3.getText().toString().trim().isEmpty()||
                        inputcode4.getText().toString().trim().isEmpty()||
                        inputcode5.getText().toString().trim().isEmpty()||
                        inputcode6.getText().toString().trim().isEmpty()
                ){

                    Toast.makeText(verifyotp.this, "Please Enter Valid Code", Toast.LENGTH_SHORT).show();
                    return;
                }

                String code=inputcode1.getText().toString()+inputcode2.getText().toString()
                        +inputcode3.getText().toString()+inputcode4.getText().toString()
                        +inputcode5.getText().toString()+inputcode6.getText().toString();

                if(verificationId !=null){
                    progressBar.setVisibility(View.VISIBLE);
                    buttonverify.setVisibility(View.INVISIBLE);

                    PhoneAuthCredential phoneAuthCredential =PhoneAuthProvider.getCredential(verificationId,code);

                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressBar.setVisibility(View.GONE);
                            buttonverify.setVisibility(View.GONE);
                            if(task.isSuccessful()){

                                Intent i =new Intent(verifyotp.this,MainActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();

                            }else{

                                Toast.makeText(verifyotp.this, "Invalid Code", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }



            }
        });


        resendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonverify.setVisibility(View.VISIBLE);
                inputcode1.setText("");
                inputcode2.setText("");
                inputcode3.setText("");
                inputcode4.setText("");
                inputcode5.setText("");
                inputcode6.setText("");

                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+getIntent().getStringExtra("mobile"),60,
                        TimeUnit.SECONDS,verifyotp.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onCodeSent(@NonNull String newverificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(newverificationId, forceResendingToken);

                                verificationId=newverificationId;
                                Toast.makeText(verifyotp.this, "OTP sent", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {

                                Toast.makeText(verifyotp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });





    }

    public void setupotpinputs(){
        inputcode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()){
                    inputcode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputcode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()){
                    inputcode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputcode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()){
                    inputcode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputcode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()){
                    inputcode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputcode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()){
                    inputcode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



}