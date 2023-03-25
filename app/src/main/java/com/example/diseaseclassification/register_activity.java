package com.example.diseaseclassification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register_activity extends AppCompatActivity {
EditText full_name,email,password,re_password;
Button register;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        full_name=findViewById(R.id.full_name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        re_password=findViewById(R.id.confirm_password);
        register=findViewById(R.id.register);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent=new Intent(register_activity.this,welcome_activity.class);
//                startActivity(intent);
                progressDialog.setMessage("loading...");
                progressDialog.show();
                register_user(email.getText().toString().trim(),password.getText().toString().trim());
            }
        });
    }
    private  void register_user(String email,String password)
    {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
                if (task.isCanceled())
                    Toast.makeText(register_activity.this,  "canceled", Toast.LENGTH_SHORT).show();

                if (task.isSuccessful())
                {
                    user user=new user(email,password);
                     DatabaseReference mDatabase;
// ...
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("users").child(full_name.getText().toString().trim()).setValue(user);

                    progressDialog.dismiss();
                    Toast.makeText(register_activity.this,  "added", Toast.LENGTH_SHORT).show();


                }
                else {
                    Toast.makeText(register_activity.this, " Not Registered"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }


            }
        }) ;
    }

}