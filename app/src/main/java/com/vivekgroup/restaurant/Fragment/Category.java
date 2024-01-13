package com.vivekgroup.restaurant.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vivekgroup.restaurant.Alljson.Food;
import com.vivekgroup.restaurant.Alljson.ResultFood;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.R;
import com.vivekgroup.restaurant.adapter.Category_Adapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category extends Fragment {

    RecyclerView recyclerView;
    SearchView  searchView;
    ArrayList<Food>arrayList;

    //swipe
    SwipeRefreshLayout swipeLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);


        recyclerView=view.findViewById(R.id.gridid);

        searchView=view.findViewById(R.id.categoryserarch);


        swipeLayout =view.findViewById(R.id.categoryrefersh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Refresh();

                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {

                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 4000); // Delay in millis


            }
        });
        swipeLayout .setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        arrayList=new ArrayList<Food>();
        recyclerView =view.findViewById(R.id.gridid);
        LinearLayoutManager linearLayoutManager =new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(linearLayoutManager);
        Refresh();



    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            APIInterface apiInterface =Appclient.getclient().create(APIInterface.class);
            Call <ResultFood> call =apiInterface.buyFoodsearch(s);
            call.enqueue(new Callback<ResultFood>() {
                @Override
                public void onResponse(Call<ResultFood> call, Response<ResultFood> response) {
                  arrayList= (ArrayList<Food>) response.body().getFood();
                    Log.e("check", "onResponse: " + arrayList.get(0).getFoodName());
                    Category_Adapter category_adapter =new Category_Adapter(getContext(),arrayList);
                    recyclerView.setAdapter(category_adapter);

                }

                @Override
                public void onFailure(Call<ResultFood> call, Throwable t) {

                }
            });



            return true;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    });


        return  view;
    }
    public void Refresh(){
        APIInterface apiInterface =Appclient.getclient().create(APIInterface.class);
        Call<ResultFood> call= apiInterface.getfoods();
        call.enqueue(new Callback<ResultFood>() {
            @Override
            public void onResponse(Call<ResultFood> call, Response<ResultFood> response) {
                try {
                    arrayList= (ArrayList<Food>) response.body().getFood();
                    Log.e("check", "onResponse: " + arrayList.get(0).getFoodName());
                    Category_Adapter category_adapter =new Category_Adapter(getContext(),arrayList);
                    recyclerView.setAdapter(category_adapter);
                }catch (Exception e){
                    Toast.makeText(getContext(), "failed"+e, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResultFood> call, Throwable t) {

            }
        });

    }


}