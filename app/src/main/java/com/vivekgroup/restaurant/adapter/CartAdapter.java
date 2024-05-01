package com.vivekgroup.restaurant.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;
import com.vivekgroup.restaurant.Alljson.CartFood;
import com.vivekgroup.restaurant.Alljson.ResultCartFood;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.Fragment.Cart;
import com.vivekgroup.restaurant.R;
import com.vivekgroup.restaurant.shopping.Order;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Myclass> {
    Context context;
    ArrayList<CartFood> arrayList;

    public CartAdapter(Context context, ArrayList<CartFood> arrayList)
    {
        this.context =context;
        this.arrayList =arrayList;
    }

    public CartAdapter(Cart cart, ArrayList<CartFood> arrayList){

    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_custom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {

    Picasso.get().load(arrayList.get(position).getUrl()).into(holder.boimg);
    holder.botitle.setText(arrayList.get(position).getCategory());
    holder.boedition.setText(arrayList.get(position).getFoodName());
    holder.boprice.setText(arrayList.get(position).getFoodPrice());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {
        ImageView boimg;
        TextView botitle, boedition, boprice;
        Button boremove, bobuy;
        public Myclass(@NonNull View itemView) {
            super(itemView);

            boimg =itemView.findViewById(R.id.cimg1);
            botitle =itemView.findViewById(R.id.cbooktitle);
            boedition =itemView.findViewById(R.id.cartedition);
            boprice =itemView.findViewById(R.id.cartprice);
            boremove =itemView.findViewById(R.id.remove);
            bobuy =itemView.findViewById(R.id.buycard);

            boremove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SharedPreferences preferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    String User_id= preferences.getString("User_id",null);

                    APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
                    Call<ResultCartFood> call=apiInterface.removecart(User_id,arrayList.get(getAdapterPosition()).getCartId());
                    call.enqueue(new Callback<ResultCartFood>() {
                        @Override
                        public void onResponse(Call<ResultCartFood> call, Response<ResultCartFood> response) {
                            Toast.makeText(context, "Card is Removed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResultCartFood> call, Throwable t) {

                        }
                    });
                }
            });

            bobuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(context, Order.class);
                    intent.putExtra("card_id",arrayList.get(getAdapterPosition()).getCartId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
