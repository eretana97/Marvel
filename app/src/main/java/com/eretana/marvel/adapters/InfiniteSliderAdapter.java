package com.eretana.marvel.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.eretana.marvel.R;
import com.eretana.marvel.model.Character;
import com.eretana.marvel.view.Main;

import java.util.List;

public class InfiniteSliderAdapter extends RecyclerView.Adapter<InfiniteSliderAdapter.SlideViewHolder> {

    List<Character> characters;
    ViewPager2 viewPager2;

    public InfiniteSliderAdapter(List<Character> characters, ViewPager2 viewPager2) {
        this.characters = characters;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        holder.setItem(characters.get(position));
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }



    class SlideViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCharacterName;
        private TextView tvCharacterDescription;
        private TextView tvNComics;
        private ImageView ivCharacterImage;
        private Context ctx;
        private Button btn_more;

        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            ctx = itemView.getContext();
            tvNComics = itemView.findViewById(R.id.tv_n_comics);
            tvCharacterName = itemView.findViewById(R.id.tv_character_name);
            ivCharacterImage = itemView.findViewById(R.id.iv_character_image);
            tvCharacterDescription = itemView.findViewById(R.id.tv_character_description);
            btn_more = itemView.findViewById(R.id.btn_view_details);
        }

        void setItem(Character character){
            tvCharacterName.setText(character.name);
            tvCharacterDescription.setText(character.description);

            String availablecomics = itemView.getResources().getQuantityString(R.plurals.available_comics,character.comics.available, character.comics.available);
            tvNComics.setText(availablecomics);

            Glide.with(ctx)
                    .load(character.thumbnail.path + "." + character.thumbnail.extension)
                    .into(ivCharacterImage);

            btn_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = characters.get(getAdapterPosition()).id;
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",id);
                    Main.navController.navigate(R.id.fragment_inifinity_slider_to_fragment_character_info,bundle);
                }
            });
        }
    }

}
