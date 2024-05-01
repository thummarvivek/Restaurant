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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vivekgroup.restaurant.Alljson.Food;
import com.vivekgroup.restaurant.Alljson.FoodCart;
import com.vivekgroup.restaurant.Alljson.ResultFgcart;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.R;
import com.vivekgroup.restaurant.shopping.Selectcart;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.Myclass> {

    Context context;

    ArrayList<Food>arrayList;
    ArrayList<FoodCart> arrayList2;

    public Category_Adapter(Context context, ArrayList<Food> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_custom,parent,false);
       return new Category_Adapter.Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        holder.catext1.setText(arrayList.get(position).getCategory());
        holder.catext2.setText(arrayList.get(position).getFoodName());
        holder.offprice.setText(arrayList.get(position).getFoodOff());
        Picasso.get().load(arrayList.get(position).getUrl()).placeholder(R.drawable.foodplaceb).into(holder.caimg);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {

        TextView catext1,catext2,offprice;
        ImageView caimg;

        CardView cardView;
        String sta;
        Button addbtn;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            catext1 =itemView.findViewById(R.id.cbooktitle);
            catext2 =itemView.findViewById(R.id.cartedition);
            caimg =itemView.findViewById(R.id.cimg1);
            cardView = itemView.findViewById(R.id.cardand);
            addbtn = itemView.findViewById(R.id.adcard);
            offprice = itemView.findViewById(R.id.priceoff);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(context , Selectcart.class);
                    intent.putExtra("categoryid",arrayList.get(getAdapterPosition()).getFoodId());
                    context.startActivity(intent);
                }
            });

            addbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences preferences= context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    String User_id= preferences.getString("User_id",null);
                    sta ="1";

                    APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
                    Call<ResultFgcart> call=apiInterface.selectcart(User_id,arrayList.get(getAdapterPosition()).getFoodId(),sta);
                    call.enqueue(new Callback<ResultFgcart>() {
                        @Override
                        public void onResponse(Call<ResultFgcart> call, Response<ResultFgcart> response) {
                            Toast.makeText(context, "go to Cart", Toast.LENGTH_SHORT).show();
                            Toast.makeText(itemView.getContext(), ""+User_id+arrayList.get(getAdapterPosition()).getFoodId()+sta, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResultFgcart> call, Throwable t) {

                        }
                    });

                }
            });
        }
    }
}
