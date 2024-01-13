package com.vivekgroup.restaurant.Loginpro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.vivekgroup.restaurant.Alljson.FoodTable;
import com.vivekgroup.restaurant.Alljson.ResultFoodTable;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.Homepage.Homepage;
import com.vivekgroup.restaurant.R;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Activity;

public class Secondpay extends AppCompatActivity implements PaymentResultListener {

    private static final String CHANNEL_ID="My Channal";
    RecyclerView recyclerView;
    ArrayList<FoodTable>arrayList2;
    private static final int NOTIFICATION_ID = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpay);
        arrayList2= new ArrayList<>();
        SwipeRefreshLayout refreshLayout;

          paybtn();
    }

    public void paybtn(){
        SharedPreferences preferences =getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences table1 =getSharedPreferences("Table", Context.MODE_PRIVATE);
        String user_name = preferences.getString("Username",null);
        String Phone_no = preferences.getString("Phone_no",null);
        String Email = preferences.getString("Email",null);
        String Profilepicture = preferences.getString("Profilepicture",null);
        String Total = table1.getString("Total",null);


        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_JxydaN4pzceXhD");

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.smslogo);

        /**
         * Reference to current activity
         */
                    final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {

            JSONObject options = new JSONObject();
            options.put("name", user_name);
            options.put("description", "Reference No. #123456");
//                        options.put("image", Profilepicture);
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#522A2A");
            options.put("currency", "INR");
            options.put("amount", Total+"00");//pass amount in currency subunits
            options.put("prefill.email",Email );
            options.put("prefill.contact",Phone_no);
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Toast.makeText(activity, ""+e, Toast.LENGTH_SHORT).show();

        }

    }
    @Override
    public void onPaymentSuccess(String s) {
        pay();
        Intent intent=new Intent(Secondpay.this, Homepage.class);
        startActivity(intent);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(Secondpay.this, "Please try again."+s, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Secondpay.this, Homepage.class);
        startActivity(intent);
    }
    public void pay(){
        try {
            SharedPreferences table1 =getSharedPreferences("Table", Context.MODE_PRIVATE);
            SharedPreferences preferences =getSharedPreferences("UserData", Context.MODE_PRIVATE);
            String user_name =preferences.getString("Username",null);
            String userId =preferences.getString("User_id",null);
            String Total = table1.getString("Total",null);
            String cot = table1.getString("cot",null);
            String pers = table1.getString("pers",null);
            SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
            String currentdata =data.format(new Date());
            SimpleDateFormat detail = new SimpleDateFormat("HH:mm:ss");
            String currentime =detail.format(new Date());
            String paymentt ="Online Payment";

            APIInterface apiInterface =Appclient.getclient().create(APIInterface.class);
            Call<ResultFoodTable> call=apiInterface.instable(userId,currentdata,currentime,paymentt,Total,user_name,cot,pers);
            call.enqueue(new Callback<ResultFoodTable>() {
                @Override
                public void onResponse(Call<ResultFoodTable> call, Response<ResultFoodTable> response) {
                    arrayList2= (ArrayList<FoodTable>) response.body().getFoodTable();
                    Toast.makeText(Secondpay.this, "Your table has been booked", Toast.LENGTH_SHORT).show();
                    notifi();
                }

                @Override
                public void onFailure(Call<ResultFoodTable> call, Throwable t) {
                    Toast.makeText(Secondpay.this, ""+t, Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(Secondpay.this, ""+e, Toast.LENGTH_SHORT).show();

        }

    }

    public void notifi(){

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.smslogo, null);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

        Bitmap largeIcon = bitmapDrawable.getBitmap();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification;
        SimpleDateFormat data = new SimpleDateFormat("HH:mm");
        String currentDate = data.format(new Date());
        SharedPreferences table1 =getSharedPreferences("Table", Context.MODE_PRIVATE);
        SharedPreferences preferences =getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String user_name = preferences.getString("Username",null);
        String amo = table1.getString("Total",null);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.smslogo)
                    .setSubText(currentDate) //set title of notification
                    .setContentText("Dear Customer "+user_name+",  Your Table transaction "+amo+" rupees is successful.")//this is notification message
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.smslogo)
                    .setSubText(currentDate) //set title of notification
                    .setContentText("Dear Customer "+user_name+",  Your Table transaction "+amo+" rupees is successful.")//this is notification message
                    .build();


        }
        nm.notify(NOTIFICATION_ID, notification);


    }



}