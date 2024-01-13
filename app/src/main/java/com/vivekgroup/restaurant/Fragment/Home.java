package com.vivekgroup.restaurant.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vivekgroup.restaurant.Alljson.Gallery;
import com.vivekgroup.restaurant.Alljson.ResultGallery;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.R;
import com.vivekgroup.restaurant.Loginpro.Secondpay;
import com.vivekgroup.restaurant.adapter.Gallery_Adapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home extends Fragment  {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Gallery>arrayList;
    private static final String CHANNEL_ID="My Channal";
    private static final int NOTIFICATION_ID = 102;
    Button paybtn;
    EditText notable,person;

    //swipe represh
    SwipeRefreshLayout refreshLayout;
      boolean b = false;


    public Home(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        arrayList= new ArrayList<>();
        toolbar =view.findViewById(R.id.toolbarhome);
        notable =view.findViewById(R.id.notable);
        paybtn =view.findViewById(R.id.paytable);
        person =view.findViewById(R.id.Person3);
        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();

        appCompatActivity.setSupportActionBar(toolbar);



        //refresh code
        refreshLayout =view.findViewById(R.id.homerefersh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {



                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {

                        // Stop animation (This will be after 3 seconds)
                        refreshLayout.setRefreshing(false);
                    }
                }, 1000); // Delay in millis

            }
        });

        refreshLayout .setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        arrayList=new ArrayList<Gallery>();
        recyclerView =view.findViewById(R.id.lvv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        Refresh();


        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences table1 =getContext().getSharedPreferences("Table", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=table1.edit();
                int per =Integer.parseInt(person.getText().toString());
                int tablee =Integer.parseInt(notable.getText().toString());
                int utable = 300;
                int Tot =utable*tablee;
                ed.putString("Total", String.valueOf(Tot));
                ed.putString("cot",  String.valueOf(tablee));
                ed.putString("pers", String.valueOf(per));
                ed.putBoolean("is_tab",true);
                ed.apply();
                Intent intent =new Intent(getContext(), Secondpay.class);
                startActivity(intent);
            }
        });

        return view;
    }
    public void Refresh(){
        APIInterface apiInterface =Appclient.getclient().create(APIInterface.class);
        Call<ResultGallery> call=apiInterface.gethall();
        call.enqueue(new Callback<ResultGallery>() {
            @Override
            public void onResponse(Call<ResultGallery> call, Response<ResultGallery> response) {
                arrayList= (ArrayList<Gallery>) response.body().getGallery();
                Gallery_Adapter gallery_adapter =new Gallery_Adapter(getContext(),arrayList);
                recyclerView.setAdapter(gallery_adapter);

            }

            @Override
            public void onFailure(Call<ResultGallery> call, Throwable t) {

            }
        });
    }
}