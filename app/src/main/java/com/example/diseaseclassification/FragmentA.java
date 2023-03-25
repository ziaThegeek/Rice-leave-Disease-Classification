package com.example.diseaseclassification;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;


public class FragmentA extends Fragment {
    String api_id="615616ee57d2320279dc3cc5dfa0d0f5";
    public  FusedLocationProviderClient location_provider;
    TextView take_picture,upload_picture;
    private static final int CAMERA_REQUEST = 1888,REQUEST_CODE=10,REQUST_PERMISSION_CODE=1;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v=inflater.inflate(R.layout.home_fragment,container,false);
     take_picture=v.findViewById(R.id.upload_picture);
     take_picture.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent a1=new Intent(getContext(),MainActivity.class);
             startActivity(a1);
         }
     });

         return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    }
}
