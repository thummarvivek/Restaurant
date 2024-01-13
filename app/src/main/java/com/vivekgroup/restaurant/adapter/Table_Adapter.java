package com.vivekgroup.restaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vivekgroup.restaurant.Alljson.FoodTable;
import com.vivekgroup.restaurant.R;

import java.util.ArrayList;

public class Table_Adapter extends RecyclerView.Adapter<Table_Adapter.Myclass> {
    Context context;
    ArrayList<FoodTable> arrayList;

    public Table_Adapter(Context context, ArrayList<FoodTable> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Table_Adapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_custom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Table_Adapter.Myclass holder, int position) {
        holder.t1.setText(arrayList.get(position).getUserName());
        holder.t2.setText(arrayList.get(position).getCountTable()+" table is booked.");
        holder.t3.setText(arrayList.get(position).getPerson()+" Person");
        holder.t4.setText(arrayList.get(position).getTBookDate());
        holder.t5.setText(arrayList.get(position).getTBookTime());
        holder.t6.setText(arrayList.get(position).getTPaymentType());
        holder.t7.setText(arrayList.get(position).getTAmount());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder{
        TextView t1,t2,t3,t4,t5,t6,t7;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            t1 =itemView.findViewById(R.id.uuser);
            t2 =itemView.findViewById(R.id.counttable);
            t3 =itemView.findViewById(R.id.person);
            t4 =itemView.findViewById(R.id.date);
            t5 =itemView.findViewById(R.id.time);
            t6 =itemView.findViewById(R.id.paymen);
            t7 =itemView.findViewById(R.id.price);

        }
    }
}
