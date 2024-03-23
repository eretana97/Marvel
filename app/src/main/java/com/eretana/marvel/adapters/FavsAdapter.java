package com.eretana.marvel.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eretana.marvel.R;
import com.eretana.marvel.db.CharacterRepository;
import com.eretana.marvel.model.Character;
import com.eretana.marvel.tools.DateTools;
import com.eretana.marvel.view.Main;

import java.util.List;

public class FavsAdapter extends RecyclerView.Adapter<FavsAdapter.FavsViewHolder> {

    private List<Character> characters;
    private View.OnClickListener onclickdelete;

    public FavsAdapter(List<Character> characters, View.OnClickListener onclick) {
        this.characters = characters;
        this.onclickdelete = onclick;
    }

    @NonNull
    @Override
    public FavsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite,parent,false);
        return new FavsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavsViewHolder holder, int position) {
        holder.setPosition(position);
        holder.setCharacter(characters.get(position));
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    class FavsViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name, tv_savedat;
        private Button iv_delete;
        private int position;
        private View itemview;


        public FavsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_savedat = itemView.findViewById(R.id.tv_savedat);
            this.iv_delete = itemView.findViewById(R.id.iv_delete);
            this.itemview = itemView;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public void setCharacter(Character c){
            this.tv_name.setText(c.name);
            this.tv_savedat.setText(String.format("%s %s", itemview.getContext().getResources().getString(R.string.saved_at), DateTools.formatDate(c.savedAt)));
            this.iv_delete.setTag(R.id.iv_delete,this.position);
            this.iv_delete.setOnClickListener(onclickdelete);


            this.itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",c.id);
                    Main.navController.navigate(R.id.fragment_favorites_to_fragment_characterinfo,bundle);
                }
            });
        }

    }


}
