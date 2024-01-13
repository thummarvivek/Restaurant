package com.vivekgroup.restaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vivekgroup.restaurant.Alljson.Gallery;
import com.vivekgroup.restaurant.R;

import java.util.ArrayList;

public class Gallery_Adapter extends RecyclerView.Adapter<Gallery_Adapter.Myclass> {

    Context context;
    ArrayList<Gallery>arrayList;

    public Gallery_Adapter(Context context, ArrayList<Gallery> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Gallery_Adapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_custom,parent,false);
        return new Gallery_Adapter.Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Gallery_Adapter.Myclass holder, int position) {
            holder.txt1.setText(arrayList.get(position).getGalleryName());
        Picasso.get().load(arrayList.get(position).getGalleryUrl()).placeholder(R.drawable.placeimg).into(holder.img1);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {

        TextView txt1;
        ImageView img1;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            txt1 =itemView.findViewById(R.id.txxt);
            img1 =itemView.findViewById(R.id.ccimg);
        }
    }
}
