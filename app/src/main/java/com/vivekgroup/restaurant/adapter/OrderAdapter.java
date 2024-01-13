package com.vivekgroup.restaurant.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.vivekgroup.restaurant.Alljson.Myorder;
import com.vivekgroup.restaurant.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.Myclass> {
    Context context;
    ArrayList<Myorder>arrayList;

    public OrderAdapter(Context context, ArrayList<Myorder> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.ordercustom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {

        SharedPreferences preferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String user_name = preferences.getString("Username",null);
        Picasso.get().load(arrayList.get(position).getUrl()).placeholder(R.drawable.placeimg).into(holder.oimg);
        holder.ouname.setText(user_name);
        holder.obname.setText(arrayList.get(position).getFoodName());
        holder.oedition.setText(arrayList.get(position).getCategory()+" Food");
        holder.oprice.setText(arrayList.get(position).getTotalfoodPrice());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {
        ImageView oimg;
        TextView  ouname, obname, oedition, oprice;

        public Myclass(View view) {
            super(view);
            oimg =view.findViewById(R.id.ocimg1);
            ouname =view.findViewById(R.id.ocuname);
            obname =view.findViewById(R.id.obname);
            oedition =view.findViewById(R.id.osedition);
            oprice =view.findViewById(R.id.osprice);
        }
    }
}
