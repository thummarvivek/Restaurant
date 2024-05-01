package com.vivekgroup.restaurant.Loginpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vivekgroup.restaurant.Alljson.Registration;
import com.vivekgroup.restaurant.Alljson.ResultRegistration;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forget extends AppCompatActivity {

    TextView relogin;
    EditText usern,phoneu;
    ArrayList<Registration>arrayList;
    private static final String CHANNEL_ID="My Channal";
    private static final int NOTIFICATION_ID = 100;
    ConnectivityManager connectivityManager;
    Button otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        relogin= findViewById(R.id.relog);
        otp= findViewById(R.id.getootp);
        usern =findViewById(R.id.forgetuser);
        phoneu =findViewById(R.id.forgetphoneno);
        arrayList=new ArrayList<>();




        relogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Forget.this, Login.class);
                startActivity(intent);
            }
        });



        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname =usern.getText().toString();
                String uphone =phoneu.getText().toString();
                APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
                Call<ResultRegistration> call=apiInterface.uforget(uphone);
                call.enqueue(new Callback<ResultRegistration>() {
                    @Override
                    public void onResponse(Call<ResultRegistration> call, Response<ResultRegistration> response) {
                        arrayList = (ArrayList<Registration>) response.body().getRegistration();
                        String username =arrayList.get(0).getUserName();

                        if (username.equals(uname)){
                            SharedPreferences preferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                            SharedPreferences.Editor ed=preferences.edit();
                            ed.putString("Username", (String) arrayList.get(0).getUserName());
                            ed.apply();
                            APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
                            Call<ResultRegistration> call1=apiInterface.uid(username);
                            call1.enqueue(new Callback<ResultRegistration>() {
                                @Override
                                public void onResponse(Call<ResultRegistration> call, Response<ResultRegistration> response) {
                                    arrayList = (ArrayList<Registration>) response.body().getRegistration();
                                    String userid =arrayList.get(0).getUserId();
                                    SharedPreferences preferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor ed=preferences.edit();
//                                    ed.putString("User_id",userid);
                                    ed.putString("User_id", (String) arrayList.get(0).getUserId());
                                    ed.apply();
                                }

                                @Override
                                public void onFailure(Call<ResultRegistration> call, Throwable t) {
                                    Toast.makeText(Forget.this, "error", Toast.LENGTH_SHORT).show();

                                }
                            });
                            Intent intent =new Intent(Forget.this, Otp.class);
                            startActivity(intent);
                            notifi();
                            finish();
                        }else {
                            Toast.makeText(Forget.this, "your Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResultRegistration> call, Throwable t) {
                        Toast.makeText(Forget.this, ""+t, Toast.LENGTH_SHORT).show();

                    }
                });







            }
        });



    }
    public void notifi(){


        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.smslogo, null);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

        Bitmap largeIcon = bitmapDrawable.getBitmap();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification;
        Random rand = new Random();
        int number = rand.nextInt((9999-1000) + 1);
        SharedPreferences preferences =getSharedPreferences("Otpdata" , Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=preferences.edit();
        String Otpsend =""+number;
        edit.putString("OTP",Otpsend);
        edit.putBoolean("otp_is_Done",true);
        edit.apply();

        SimpleDateFormat data = new SimpleDateFormat("HH:mm");
        String currentDate = data.format(new Date());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.smslogo)
                    .setSubText(currentDate) //set title of notification
                    .setContentText(number+" is the OTP to your Restaurant Account")//this is notification message
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.smslogo)
                    .setSubText(currentDate) //set title of notification
                    .setContentText(number+" is the OTP to your Restaurant Account")//this is notification message
                    .build();


        }
        nm.notify(NOTIFICATION_ID, notification);


    }



}

