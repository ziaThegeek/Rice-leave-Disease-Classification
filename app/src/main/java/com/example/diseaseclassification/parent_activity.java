package com.example.diseaseclassification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class parent_activity extends AppCompatActivity {

    FragmentA fragmentA;
    FragmentB fragmentB;
    FragmentC fragmentC;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);


bottomNavigationView.setOnItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FragmentA()).commit();

    }
    BottomNavigationView.OnNavigationItemSelectedListener navListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected( MenuItem item) {
         androidx.fragment.app.Fragment selected_fragment=null;
            switch(item.getItemId())
            {
                case R.id.home:
                    selected_fragment=new FragmentA();
                    break;
                case R.id.faqs:
                    selected_fragment=new FragmentB();
                    break;
                case R.id.info_center:
                    selected_fragment=new FragmentC();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,selected_fragment).commit();
            return true;
        }
    };
//    private void loadFragment(Fragment fragment) {
//// create a FragmentManager
//        FragmentManager fm = getFragmentManager();
//// create a FragmentTransaction to begin the transaction and replace the Fragment
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//// replace the FrameLayout with new Fragment
//        fragmentTransaction.replace(R.id.frame_layout, fragment);
//        fragmentTransaction.commit(); // save the changes
//
//    }
}