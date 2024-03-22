package com.eretana.marvel.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.eretana.marvel.R;

public class Main extends AppCompatActivity{

    public static NavController navController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        load();
    }

    private void init() {
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
    }

    private void load(){

    }


}