package com.vivekgroup.restaurant.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;

import com.vivekgroup.restaurant.Alljson.Food;
import com.vivekgroup.restaurant.Alljson.FoodCart;
import com.vivekgroup.restaurant.Alljson.ResultFgcart;
import com.vivekgroup.restaurant.Alljson.ResultFood;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.Homepage.Homepage;
import com.vivekgroup.restaurant.R;

public class Selectcart extends AppCompatActivity {

    ArrayList<Food> arrayList;
    ArrayList<FoodCart> arrayList2;
    String sta;

    Button Adcart;
    ImageView img;
    TextView btitle, foodna, foodprice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcart);
        img = findViewById(R.id.selectimg);
        btitle = findViewById(R.id.bname);
        foodna = findViewById(R.id.bedition);
        foodprice = findViewById(R.id.bprice);
        Adcart =findViewById(R.id.addtocart);
        arrayList = new ArrayList<>();
        arrayList2 = new ArrayList<>();

        Intent intent = getIntent();
        String category_id = intent.getStringExtra("categoryid");

        APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
        Call<ResultFood>call= apiInterface.selectfood(category_id);
        call.enqueue(new Callback<ResultFood>() {
            @Override
            public void onResponse(Call<ResultFood> call, Response<ResultFood> response) {
                try {

                    arrayList =(ArrayList<Food>)response.body().getFood();

                    Picasso.get().load(arrayList.get(0).getUrl()).into(img);

                    String name = arrayList.get(0).getCategory();
                    btitle.setText(name);

                    String foodn = arrayList.get(0).getFoodName();
                    foodna.setText( foodn);

                    String foodPrice = arrayList.get(0).getFoodPrice();
                    foodprice.setText(foodPrice);


                } catch (Exception e) {
                    Toast.makeText(Selectcart.this, "this App does not have a data " + e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultFood> call, Throwable t) {
                Toast.makeText(Selectcart.this, "error : " + t, Toast.LENGTH_SHORT).show();
            }
        });

        SharedPreferences preferences= getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String User_id= preferences.getString("User_id",null);
        sta="1";

        Adcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                APIInterface apiInterface1 =Appclient .getclient().create(APIInterface.class);
                Call<ResultFgcart> call=apiInterface1.selectcart(User_id,category_id,sta);
                call.enqueue(new Callback<ResultFgcart>() {
                    @Override
                    public void onResponse(Call<ResultFgcart> call, Response<ResultFgcart> response) {
                        Toast.makeText(Selectcart.this, "go to Cart", Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(Selectcart.this, Homepage.class);
                        startActivity(intent1);
                    }

                    @Override
                    public void onFailure(Call<ResultFgcart> call, Throwable t) {
                        Toast.makeText(Selectcart.this, "process are failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }
}