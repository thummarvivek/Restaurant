package com.vivekgroup.restaurant.Loginpro;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vivekgroup.restaurant.Alljson.ResultRegistration;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import retrofit2.Call;

public class Otp extends AppCompatActivity {

    Button ssettotp;
    private static final String CHANNEL_ID="My Channal";
    private static final int NOTIFICATION_ID = 101;


    EditText gotp;
    TextView resetotp;
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ssettotp =findViewById(R.id.ssetotpbtn);
        gotp =findViewById(R.id.settotp);
        resetotp =findViewById(R.id.reotp);

        resetotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifi();
            }
        });


        ssettotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences =getSharedPreferences("Otpdata", Context.MODE_PRIVATE);
                String otprecived = preferences.getString("OTP",null);
                String ootp= gotp.getText().toString();
                if (otprecived.equals(ootp)){
                    Intent intent =new Intent(Otp.this,ResetPaasword.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Otp.this, "Don't match your otp", Toast.LENGTH_SHORT).show();
                }


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