package com.eretana.marvel.view.fragments;

import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eretana.marvel.R;
import com.eretana.marvel.adapters.InfiniteSliderAdapter;
import com.eretana.marvel.model.Character;
import com.eretana.marvel.view.Main;
import com.eretana.marvel.viewmodel.MainVM;
import com.eretana.marvel.viewmodel.SharedVM;

import java.util.ArrayList;
import java.util.List;

public class InfiniteSlider extends Fragment implements Observer<List<Character>> {

    private View fragmentView;
    private MainVM vm;
    private ViewPager2 viewPager2;
    private List<Character> characters;
    private InfiniteSliderAdapter adapter;
    private Button btn_favlist;
    private EditText et_search;
    private static int limit = 5;
    private static int offset = 0;
    private static String searchname = null;
    private SharedVM shared;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_infinite_slider, container, false);
        shared = new ViewModelProvider(requireActivity()).get(SharedVM.class);
        init();
        saveSharedData();
        load();
        return fragmentView;
    }

    private void init() {
        vm = new MainVM();
        characters = new ArrayList<>();
        et_search = fragmentView.findViewById(R.id.et_search);
        viewPager2 = fragmentView.findViewById(R.id.vp_slider);
        btn_favlist = fragmentView.findViewById(R.id.btn_favlist);
        adapter = new InfiniteSliderAdapter(characters,viewPager2);
    }



    private void load(){
        viewPager2.setAdapter(adapter);
        vm.getCharaters().observe(getViewLifecycleOwner(),this);

        if(!shared.isSaved()){
            vm.callService(searchname,limit,offset);
        }else{
            characters.clear();
            vm.callService(shared.getSearchname(),shared.getOffset()+5,0);
        }

        btn_favlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main.navController.navigate(R.id.fragment_inifinity_slider_to_fragment_favorites);
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int pagehorizon = offset + limit - 1;
                if(position == pagehorizon){
                    offset += 5;
                    vm.callService(searchname,limit,offset);
                }
                saveSharedData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(event == null || event.getAction() != KeyEvent.ACTION_DOWN){
                    return false;
                }

                if(actionId == EditorInfo.IME_ACTION_SEARCH){

                    searchname = v.getText().toString();

                    if(searchname.isEmpty()){
                        searchname = null;
                    }

                    offset = 0;
                    characters.clear();
                    vm.callService(searchname,limit,offset);
                    closeKeyboard(v);
                    saveSharedData();
                }

                return false;
            }
        });

    }

    private void saveSharedData(){
        shared.setCurrentPage(viewPager2.getCurrentItem());
        shared.setLimit(limit);
        shared.setOffset(offset);
        shared.setSearchname(searchname);
        shared.setSaved(true);
    }



    @Override
    public void onChanged(List<Character> cs) {
        for(Character c : cs){
            if(!characters.contains(c)){
                characters.add(c);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void closeKeyboard(View v){
        InputMethodManager imm = (InputMethodManager) fragmentView.getContext().getSystemService(fragmentView.getContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}