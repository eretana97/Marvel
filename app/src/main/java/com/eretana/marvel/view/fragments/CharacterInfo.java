package com.eretana.marvel.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eretana.marvel.R;
import com.eretana.marvel.db.CharacterRepository;
import com.eretana.marvel.model.Character;
import com.eretana.marvel.viewmodel.InfoVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CharacterInfo extends Fragment implements Observer<Character>, View.OnClickListener {

    private View fragmentView;
    private InfoVM vm;
    private TextView tvCharacterName,tvCharacterDescription;
    private ImageView ivCharacterImage;
    private Bundle bundle;
    private FloatingActionButton fabFavorite;
    private CharacterRepository repository;
    private Character character;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_character_info, container, false);

        init();
        load();

        return fragmentView;
    }

    private void init(){
        bundle = getArguments();
        vm = new InfoVM(fragmentView.getContext());
        repository = new CharacterRepository(this.getContext());
        fabFavorite = fragmentView.findViewById(R.id.fab_favorite);
        tvCharacterName = fragmentView.findViewById(R.id.tv_character_name);
        ivCharacterImage = fragmentView.findViewById(R.id.iv_character_image);
        tvCharacterDescription = fragmentView.findViewById(R.id.tv_character_description);
    }

    private void load(){
        fabFavorite.setOnClickListener(this);

        vm.getCharacterInfo().observe(getViewLifecycleOwner(),this);
        vm.callService(bundle.getInt("id"));


    }

    @Override
    public void onClick(View v) {
        if(character != null) {
            repository.insertFavorite(character);
            Toast.makeText(fragmentView.getContext(),"SAVED ON FAVORITES",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onChanged(Character character) {
        this.character = character;
        Glide.with(fragmentView.getContext()).load(character.thumbnail.path + "." + character.thumbnail.extension).into(ivCharacterImage);
        tvCharacterName.setText(character.name);

        if(!character.description.trim().isEmpty()){
            tvCharacterDescription.setText(character.description);
        }else{
            tvCharacterDescription.setText("No description available");
        }

    }
}