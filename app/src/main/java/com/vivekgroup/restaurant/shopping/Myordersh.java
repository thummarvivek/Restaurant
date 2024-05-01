package com.vivekgroup.restaurant.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import android.os.Bundle;

import com.vivekgroup.restaurant.Alljson.Myorder;
import com.vivekgroup.restaurant.Alljson.Resultmyorder;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.R;
import com.vivekgroup.restaurant.adapter.OrderAdapter;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Myordersh extends AppCompatActivity {
    SwipeRefreshLayout swipeLayout;

    ArrayList<Myorder> arrayList;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myordersh);
        swipeLayout =findViewById(R.id.swiporshow);


        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Refresh();

                // Your code here
                Toast.makeText(getApplicationContext(), "page are refresh", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {


                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });

        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        arrayList=new ArrayList<Myorder>();
        recyclerView=findViewById(R.id.orderrv);
        LinearLayoutManager linearLayoutManager =new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(linearLayoutManager);

        Refresh();


    }

    public void Refresh(){
        SharedPreferences preferences =getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String user_id = preferences.getString("User_id",null);

        APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
        Call<Resultmyorder> call=apiInterface.myorders(user_id);
        call.enqueue(new Callback<Resultmyorder>() {
            @Override
            public void onResponse(Call<Resultmyorder> call, Response<Resultmyorder> response) {
                arrayList= (ArrayList<Myorder>) response.body().getMyorder();
                OrderAdapter orderAdapter =new OrderAdapter(Myordersh.this ,arrayList);
                Log.e("check", "onResponse: "+arrayList.get(0).getUrl());
                Log.e("check", "onResponse: "+arrayList.get(0).getFoodName());
                Log.e("check", "onResponse: "+arrayList.get(0).getCategory());
                Log.e("check", "onResponse: "+arrayList.get(0).getFoodPrice());
                recyclerView.setAdapter(orderAdapter);

            }

            @Override
            public void onFailure(Call<Resultmyorder> call, Throwable t) {

            }
        });

    }

}