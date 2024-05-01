package com.vivekgroup.restaurant.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.vivekgroup.restaurant.Alljson.CartFood;
import com.vivekgroup.restaurant.Alljson.ResultCartFood;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.Homepage.Homepage;
import com.vivekgroup.restaurant.R;
import com.vivekgroup.restaurant.adapter.CartAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Cart extends Fragment {

    SwipeRefreshLayout refreshpage;
    ArrayList<CartFood> arrayList;

    ImageView emtimg;
    TextView emtext ,emtxt;
    Button embtn;
    View emview;
    Toolbar toolbar;
    RecyclerView recyclerView;



    public Cart(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view= inflater.inflate(R.layout.fragment_cart, container, false);


        toolbar =view.findViewById(R.id.toolbarcart );
        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();
        appCompatActivity.setSupportActionBar(toolbar);


        refreshpage =view.findViewById(R.id.cartswip);
        emtimg =view.findViewById(R.id.emimg01);
        emview =view.findViewById(R.id.emp02);
        emtext =view.findViewById(R.id.emte03);
        emtxt =view.findViewById(R.id.emte04);
        embtn =view.findViewById(R.id.embt05);

        emtimg.setVisibility(View.GONE);
        emview.setVisibility(View.GONE);
        emtext.setVisibility(View.GONE);
        emtxt.setVisibility(View.GONE);
        embtn.setVisibility(View.GONE);
        arrayList=new ArrayList<>();

        refreshpage.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Refresh();

                // Your code here
                Toast.makeText(getContext(), "page are refresh", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {


                        // Stop animation (This will be after 3 seconds)
                        refreshpage.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });

        // Scheme colors for animation
        refreshpage.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        arrayList=new ArrayList<CartFood>();
        recyclerView =view.findViewById(R.id.cartrv);
        LinearLayoutManager linearLayoutManager =new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(linearLayoutManager);
        Refresh();


        return view;
    }
    public void Refresh() {

        SharedPreferences preferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String User_id = preferences.getString("User_id", null);

        APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
        Call<ResultCartFood> call = apiInterface.showcart(User_id);
        call.enqueue(new Callback<ResultCartFood>() {
            @Override
            public void onResponse(Call<ResultCartFood> call, Response<ResultCartFood> response) {
                try {
                    arrayList = (ArrayList<CartFood>) response.body().getCartFood();
                    CartAdapter cartAdapter = new CartAdapter(getContext(), arrayList);
                    recyclerView.setAdapter(cartAdapter);
                } catch (Exception exception) {
                    Toast.makeText(getContext(), "data is not fetch", Toast.LENGTH_SHORT).show();
                    emtimg.setVisibility(View.VISIBLE);
                    emview.setVisibility(View.VISIBLE);
                    emtext.setVisibility(View.VISIBLE);
                    emtxt.setVisibility(View.VISIBLE);
                    embtn.setVisibility(View.VISIBLE);
                    refreshpage.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ResultCartFood> call, Throwable t) {
                emtimg.setVisibility(View.VISIBLE);
                emview.setVisibility(View.VISIBLE);
                emtext.setVisibility(View.VISIBLE);
                emtxt.setVisibility(View.VISIBLE);
                embtn.setVisibility(View.VISIBLE);
                refreshpage.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                Toast.makeText(getContext(), "" + t, Toast.LENGTH_SHORT).show();


                embtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getActivity(), Homepage.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

}