package com.eretana.marvel.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginVM extends ViewModel {

    private FirebaseAuth fauth;
    private MutableLiveData<FirebaseUser> currentUser = new MutableLiveData<>();

    public LoginVM(){
        fauth = FirebaseAuth.getInstance();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return currentUser;
    }

    public void attempt(String email, String password){
        fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                currentUser.setValue(fauth.getCurrentUser());
            }else{
                currentUser.setValue(null);
            }
        });
    }

}
