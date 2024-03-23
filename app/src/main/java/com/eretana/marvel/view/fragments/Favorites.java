package com.eretana.marvel.view.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eretana.marvel.R;
import com.eretana.marvel.adapters.FavsAdapter;
import com.eretana.marvel.db.CharacterRepository;
import com.eretana.marvel.model.Character;
import com.eretana.marvel.viewmodel.FavsVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Favorites extends Fragment implements Observer<List<Character>>, View.OnClickListener {

    private View fragmentView;
    private FavsVM vm;
    private FavsAdapter adapter;
    private RecyclerView rcv_favs;
    private List<Character> characters;
    private CharacterRepository repository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_favorites, container, false);
        init();
        load();
        return fragmentView;
    }

    private void init(){
        characters = new ArrayList<>();
        repository = new CharacterRepository(this.getContext());
        vm = new FavsVM(this.requireActivity().getApplication());
        rcv_favs = fragmentView.findViewById(R.id.rcv_favs);
        adapter = new FavsAdapter(characters, this);
    }

    private void load(){
        rcv_favs.setAdapter(adapter);
        rcv_favs.setLayoutManager(new LinearLayoutManager(this.getContext()));
        vm.getFavorites().observe(getViewLifecycleOwner(), this);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onChanged(List<Character> cs) {
        characters.addAll(cs);
        Log.d("MARVEL_FAVS","CHARACTERS:" + cs.size());
        adapter.notifyDataSetChanged();
    }

    /*ON CLICK DELETE*/
    @Override
    public void onClick(View v) {
        int position = Integer.parseInt(v.getTag(R.id.iv_delete).toString());
        Character c = characters.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(fragmentView.getContext());
        builder.setTitle(getResources().getString(R.string.remove_from_favorites));
        builder.setCancelable(true);

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                repository.deleteFavorite(c);
                characters.remove(c);
                adapter.notifyDataSetChanged();
            }
        });
        builder.create().show();
    }
}