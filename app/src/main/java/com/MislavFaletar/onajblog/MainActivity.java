package com.MislavFaletar.onajblog;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.MislavFaletar.onajblog.Fragments.FragmentStart;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupStartFragment();
    }

    private void setupStartFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        
        //ucitava start fragment
        FragmentStart fragment = new FragmentStart();
        transaction.add(R.id.frame, fragment);
        transaction.commit();
    }

}