package com.eretana.marvel.view.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eretana.marvel.R;
import com.eretana.marvel.adapters.InfiniteSliderAdapter;
import com.eretana.marvel.model.Character;
import com.eretana.marvel.view.Main;
import com.eretana.marvel.viewmodel.MainVM;

import java.util.ArrayList;
import java.util.List;

public class InfiniteSlider extends Fragment implements Observer<List<Character>> {

    private View fragmentView;
    private MainVM vm;
    private ViewPager2 viewPager2;
    private List<Character> characters;
    private InfiniteSliderAdapter adapter;
    private SearchView searchView;

    private static int limit = 5;
    private static int offset = 0;
    private static String searchname = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_infinite_slider, container, false);
        init();
        load();
        return fragmentView;
    }

    private void init() {
        vm = new MainVM();
        characters = new ArrayList<>();
        searchView = fragmentView.findViewById(R.id.sv_search);
        viewPager2 = fragmentView.findViewById(R.id.vp_slider);
        adapter = new InfiniteSliderAdapter(characters,viewPager2, fragmentView.getContext());
    }

    private void load(){
        viewPager2.setAdapter(adapter);
        vm.getCharaters().observe(this,this);
        vm.callService(searchname,limit,offset);

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
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchname = query;
                offset = 0;
                characters.clear();
                vm.callService(searchname,limit,offset);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchname = null;
                offset = 0;
                characters.clear();
                vm.callService(searchname,limit,offset);
                return false;
            }
        });
    }



    @Override
    public void onChanged(List<Character> cs) {
        characters.addAll(cs);
        adapter.notifyDataSetChanged();
    }
}