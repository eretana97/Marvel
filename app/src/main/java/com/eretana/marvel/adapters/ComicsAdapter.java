package com.eretana.marvel.adapters;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eretana.marvel.R;
import com.eretana.marvel.model.Comic;
import com.eretana.marvel.model.Url;

import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.ComicsVH> {

    List<Comic> comics;

    public ComicsAdapter(List<Comic> comics) {
        this.comics = comics;
    }

    @NonNull
    @Override
    public ComicsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic,parent,false);
        return new ComicsVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicsVH holder, int position) {
        holder.setComic(comics.get(position));
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    class ComicsVH extends RecyclerView.ViewHolder{

        private TextView tv_name;
        private TextView tv_price;
        private Button btn_link;
        private ImageView iv_image;
        private View v;


        public ComicsVH(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            tv_name = itemView.findViewById(R.id.tv_title);
            tv_price = itemView.findViewById(R.id.tv_price);
            btn_link = itemView.findViewById(R.id.btn_web_url);
            iv_image = itemView.findViewById(R.id.iv_comic_image);
        }

        public void setComic(Comic comic){
            Log.d("MARVEL_COMICS","COMICS ADAPTER" + comic.title);
            tv_name.setText(comic.title);
            tv_price.setText(String.format("$%s", comic.prices.get(0).price));
            Glide.with(v.getContext()).load(comic.thumbnail.path + "." + comic.thumbnail.extension).into(iv_image);
            btn_link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(comic.urls.get(0).url));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

}
