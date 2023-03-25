package com.example.diseaseclassification;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {
    TextView ask_qustion;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.faq_fragment,container,false);
        ask_qustion=v.findViewById(R.id.ask_qustion);
        ask_qustion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ask=new Intent(getContext(), com.example.diseaseclassification.ask_qustion.class);

                startActivity(ask);
            }
        });
        return v;
    }
}
