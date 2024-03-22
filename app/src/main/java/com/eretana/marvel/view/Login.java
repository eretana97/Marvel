package com.eretana.marvel.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eretana.marvel.R;
import com.eretana.marvel.viewmodel.LoginVM;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener, Observer<FirebaseUser> {

    private LoginVM vm;
    private Button btnLogin;
    private EditText etEmail,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        load();
    }

    private void init(){
        vm = new LoginVM();
        btnLogin = findViewById(R.id.btn_login);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
    }

    private void load(){
        btnLogin.setOnClickListener(this);
        vm.getCurrentUser().observe(this, this);
    }

    @Override
    public void onClick(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        vm.attempt(email,password);
    }

    @Override
    public void onChanged(FirebaseUser firebaseUser) {
        if(firebaseUser != null){
            Intent intent = new Intent(Login.this,Main.class);
            Login.this.startActivity(intent);
            this.finish();
        }else{
            Toast.makeText(Login.this,"LOGIN FAILED!",Toast.LENGTH_LONG).show();
        }
    }
}