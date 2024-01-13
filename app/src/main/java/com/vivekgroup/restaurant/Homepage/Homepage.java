package com.vivekgroup.restaurant.Homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vivekgroup.restaurant.Fragment.Cart;
import com.vivekgroup.restaurant.Fragment.Category;
import com.vivekgroup.restaurant.Fragment.Home;
import com.vivekgroup.restaurant.Fragment.Profile;
import com.vivekgroup.restaurant.R;

public class Homepage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {
    BottomNavigationView bnv;
    Vibrator vibrator;
    FrameLayout frameLayout;

    //swip
    ViewPager viewPager;

    //map
    private static Location location;

    private   boolean b = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        frameLayout=findViewById(R.id.container);
        loadfragment(new Home());
        bnv=findViewById(R.id.bv);
        bnv.setOnNavigationItemSelectedListener(this);
        vibrator =(Vibrator)  getSystemService(Context.VIBRATOR_SERVICE);


    }
    public  boolean loadfragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,fragment)
                    .commit();

        }
        return true;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new Home();
                //swip ke liye
//                viewPager.setCurrentItem(0);
                vibrator.vibrate(50);
                break;
            case R.id.navigation_category:
                fragment = new Category();
//                viewPager.setCurrentItem(1);
                vibrator.vibrate(50);
                break;
            case R.id.navigation_cart:
                fragment = new Cart();
//                viewPager.setCurrentItem(3);
                vibrator.vibrate(50);
                break;
            case R.id.navigation_profile:
                fragment = new Profile();
//                viewPager.setCurrentItem(4);
                vibrator.vibrate(50);
                break;
        }
        return loadfragment(fragment);
     }
    }