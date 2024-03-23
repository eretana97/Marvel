package com.eretana.marvel.view.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eretana.marvel.R;
import com.eretana.marvel.adapters.ComicsAdapter;
import com.eretana.marvel.db.CharacterRepository;
import com.eretana.marvel.model.Character;
import com.eretana.marvel.model.Comic;
import com.eretana.marvel.viewmodel.InfoVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CharacterInfo extends Fragment implements Observer<Character>, View.OnClickListener {

    private View fragmentView;
    private InfoVM vm;
    private TextView tvCharacterName,tvCharacterDescription;
    private ImageView ivCharacterImage;
    private Bundle bundle;
    private FloatingActionButton fabFavorite;
    private CharacterRepository repository;
    private Character character;
    private Drawable fabicon;
    private boolean favState;
    private List<Comic> comics;
    private RecyclerView rcvComics;
    private ComicsAdapter adapter;


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
        comics = new ArrayList<>();
        adapter = new ComicsAdapter(comics);
        vm = new InfoVM(fragmentView.getContext());
        rcvComics = fragmentView.findViewById(R.id.rcv_comics);
        repository = new CharacterRepository(this.getContext());
        fabFavorite = fragmentView.findViewById(R.id.fab_favorite);
        fabicon = fabFavorite.getDrawable();
        tvCharacterName = fragmentView.findViewById(R.id.tv_character_name);
        ivCharacterImage = fragmentView.findViewById(R.id.iv_character_image);
        tvCharacterDescription = fragmentView.findViewById(R.id.tv_character_description);
    }

    private void load(){
        fabFavorite.setOnClickListener(this);
        rcvComics.setLayoutManager(new LinearLayoutManager(fragmentView.getContext()));
        rcvComics.setAdapter(adapter);



        //OBSERVER FOR CHARACTER INFO
        vm.getCharacterInfo().observe(getViewLifecycleOwner(),this);


        //OBSERVER FOR FAVORITE STATE
        vm.isAlreadySaved(bundle.getInt("id")).observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                toggleFabButton(aBoolean);
            }
        });



        //OBSERVER FOR COMIC LIST
        vm.getComicsList().observe(getViewLifecycleOwner(), new Observer<List<Comic>>() {
            @Override
            public void onChanged(List<Comic> cs) {
                comics.addAll(cs);
                adapter.notifyDataSetChanged();
            }
        });


        vm.callService(bundle.getInt("id"));
        vm.callComicService(bundle.getInt("id"));
    }

    @Override
    public void onClick(View v) {
        if(character != null) {
            if(favState){
                repository.deleteFavorite(character);
                toggleFabButton(false);
                Toast.makeText(fragmentView.getContext(),"REMOVE FROM FAVORITES",Toast.LENGTH_SHORT).show();
            }else{
                character.savedAt = System.currentTimeMillis();
                repository.insertFavorite(character);
                toggleFabButton(true);
                Toast.makeText(fragmentView.getContext(),"SAVED ON FAVORITES",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void toggleFabButton(boolean state){

        fabicon = fabicon.mutate();
        if(state){
            //Saved on favorites
            fabicon.setTint(ContextCompat.getColor(fragmentView.getContext(), R.color.white));
        }else{
            //Delete from favorites
            fabicon.setTint(ContextCompat.getColor(fragmentView.getContext(),R.color.black));
        }

        fabFavorite.setImageDrawable(fabicon);
        favState = state;
    }

    @Override
    public void onChanged(Character character) {
        this.character = character;
        Glide.with(fragmentView.getContext()).load(character.thumbnail.path + "." + character.thumbnail.extension).into(ivCharacterImage);
        tvCharacterName.setText(character.name);

        if(!character.description.trim().isEmpty()){
            tvCharacterDescription.setText(character.description);
        }else{
            tvCharacterDescription.setText(getResources().getString(R.string.no_description));
        }

    }


}