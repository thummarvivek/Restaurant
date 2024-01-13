package com.vivekgroup.restaurant.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vivekgroup.restaurant.Alljson.CaFood;
import com.vivekgroup.restaurant.Alljson.Pay;
import com.vivekgroup.restaurant.Alljson.ResultCaFood;
import com.vivekgroup.restaurant.Alljson.ResultFoodPay;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order extends AppCompatActivity {

    TextView foodca,orname ,off ,oadd ,orphone,bname ,bprice ,fprice ,tprice ,sendamount;

    ArrayList<CaFood> arrayList;
    ImageView bimg;

    String amount ,card_id ,bookid;

    Button sendbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        arrayList=new ArrayList<>();
        foodca =findViewById(R.id.foodasd);
        orname =findViewById(R.id.ouname);
        off =findViewById(R.id.priceitem01);
        oadd =findViewById(R.id.oaddress);
        orphone =findViewById(R.id.onumber);
        bname =findViewById(R.id.bookprice1);
        bprice =findViewById(R.id.bookprice2);
        fprice =findViewById(R.id.priceitem);
        tprice =findViewById(R.id.totalamount);
        sendamount =findViewById(R.id.amountoforder);
        bimg =findViewById(R.id.boorderimg1);
        sendbtn =findViewById(R.id.orderpayment);

        SharedPreferences preferences= getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String User_id= preferences.getString("User_id",null);
        String user_name = preferences.getString("Username",null);
        String Address = preferences.getString("Address",null);
        String Phone_no = preferences.getString("Phone_no", null);


        Intent intent=getIntent();
        card_id=intent.getStringExtra("card_id");
        orname.setText(user_name);
        oadd.setText(Address);

        orphone.setText(Phone_no);

        APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
        Call <ResultCaFood> call =apiInterface.orcart(User_id,card_id);
        call.enqueue(new Callback<ResultCaFood>() {
            @Override
            public void onResponse(Call<ResultCaFood> call, Response<ResultCaFood> response) {
                try {
                    arrayList= (ArrayList<CaFood>) response.body().getCaFood();

                    Picasso.get().load(arrayList.get(0).getUrl()).into(bimg);
                    foodca.setText(arrayList.get(0).getCategory());
                    bname.setText(arrayList.get(0).getFoodName());
                    bprice.setText(arrayList.get(0).getFoodPrice());
                    fprice.setText(arrayList.get(0).getFoodPrice());
                    off.setText(arrayList.get(0).getFoodOff());

                    double originalPrice = Double.parseDouble(arrayList.get(0).getFoodPrice());
                    double secoPrice = Double.parseDouble(arrayList.get(0).getFoodDiscount());

                    // Calculate the discount (25% off)
                    double discount = secoPrice * originalPrice;

                    // Calculate the discounted price
                    double discc = originalPrice - discount;

                    String discountedPrice = String.valueOf(discc);

                    tprice.setText(discountedPrice);
                    sendamount.setText(discountedPrice);

                }catch (Exception e){
                    Toast.makeText(Order.this, "Exception"+e, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResultCaFood> call, Throwable t) {

            }
        });

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bookid = arrayList.get(0).getFoodId();
                amount =tprice.getText().toString();
                Intent intent1 =new Intent(Order.this, Payment.class);
                intent1.putExtra("amount",amount);
                intent1.putExtra("card_id",card_id );
                intent1.putExtra("bookid",bookid);
                startActivity(intent1);

            }
        });
    }
}