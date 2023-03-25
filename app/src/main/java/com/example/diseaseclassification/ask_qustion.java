package com.example.diseaseclassification;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class ask_qustion extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888,REQUEST_CODE=10,REQUST_PERMISSION_CODE=1;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    ImageView input_image;
    Button change,submit;
    EditText statement,desccription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_qustion);
        input_image = findViewById(R.id.image);
        desccription=findViewById(R.id.description);
        statement=findViewById(R.id.statement);
        change=findViewById(R.id.change);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (statement.getText().toString().isEmpty()){
                    Toast.makeText(ask_qustion.this, "statement required..", Toast.LENGTH_SHORT).show();
                    desccription.requestFocus();
                }
                else {

                    Toast.makeText(ask_qustion.this, "query submitted..", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUST_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    cameraIntent.setType("image/*");
                    startActivityForResult(cameraIntent, REQUEST_CODE);
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK||requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            input_image.setImageBitmap(photo);

        }
        if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK)
        {
            Glide.with(this).load(data.getData()).into(input_image);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, bitmap.toString(), Toast.LENGTH_SHORT).show();

        }


    }
    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions,
                                            @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUST_PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                open_file_activity();
            } else {
                Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE))
        {
            open_file_activity();
        }
        else {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},REQUST_PERMISSION_CODE);
        }
    }

    public void open_file_activity()
    {
        if (ContextCompat.checkSelfPermission(ask_qustion.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            Intent browse_file=new Intent(Intent.ACTION_OPEN_DOCUMENT);
            browse_file.setType("image/*");
//    browse_file.setType("*/*");
            startActivityForResult(browse_file,REQUEST_CODE);
        }
        else
            requestPermission();

    }


}