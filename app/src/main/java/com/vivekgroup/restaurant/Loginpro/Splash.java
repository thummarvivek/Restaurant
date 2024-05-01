package com.vivekgroup.restaurant.Loginpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.vivekgroup.restaurant.Homepage.Homepage;
import com.vivekgroup.restaurant.R;

public class Splash extends AppCompatActivity {

    private static int Screen=4000;
    ImageView image;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        image=findViewById(R.id.imageView01);
        animation = AnimationUtils.loadAnimation(this,R.anim.alpha);
        image.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                Boolean b = preferences.getBoolean("is_regi", false);
                if (b) {
                Intent intent= new Intent(Splash.this, Homepage.class);
                startActivity(intent);
                finish();
                }else {
                    Intent intent= new Intent(Splash.this, Login.class);
                    startActivity(intent);
                    finish();

                }

            }
        },Screen);
    }
}