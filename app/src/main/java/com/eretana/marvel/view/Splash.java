package com.eretana.marvel.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.eretana.marvel.R;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_DELAY = 4000;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
        load();

    }

    private void init(){
        intent = new Intent(this,Login.class);
    }

    private void load(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                Splash.this.finish();
            }
        },SPLASH_DELAY);
    }


}