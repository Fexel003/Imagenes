package com.example.volleyimages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.toolbox.NetworkImageView;
import java.util.ArrayList;

public class AdaptadorHeroes extends RecyclerView.Adapter<AdaptadorHeroes.ViewHolderHeroes> {

    ArrayList<Heroes> ltsHeroes;
    Context ctx;

    public AdaptadorHeroes(ArrayList<Heroes> ltsHeroes, Context ctx) {
        this.ltsHeroes = ltsHeroes;
        this.ctx=ctx;
    }


    @Override
    public AdaptadorHeroes.ViewHolderHeroes onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.elements_contain,null,false);
        return new ViewHolderHeroes(view);
    }

    @Override
    public void onBindViewHolder( AdaptadorHeroes.ViewHolderHeroes holder, int position) {
        holder.txtNombreHeroe.setText(ltsHeroes.get(position).getName());
        holder.networkImageViewImgHeroe.setImageUrl(ltsHeroes.get(position).getUrlImage(),VolleySingleton.getInstance(ctx).getImageLoader());
    }

    @Override
    public int getItemCount() {
        return ltsHeroes.size();
    }

    public class ViewHolderHeroes extends RecyclerView.ViewHolder {
        NetworkImageView networkImageViewImgHeroe;
        TextView txtNombreHeroe;
        public ViewHolderHeroes(View itemView) {
            super(itemView);
            networkImageViewImgHeroe=itemView.findViewById(R.id.netImVieImages);
            txtNombreHeroe=itemView.findViewById(R.id.txtNombre);
        }
    }
}
