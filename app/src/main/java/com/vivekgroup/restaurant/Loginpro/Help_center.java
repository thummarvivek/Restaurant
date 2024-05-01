package com.vivekgroup.restaurant.Loginpro;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ListView;

import com.vivekgroup.restaurant.adapter.HelpAdapter;
import com.vivekgroup.restaurant.R;


public class Help_center extends AppCompatActivity {

    ListView listView;

    String title[]={"Welcome to our Help Center for Silver Resturant! We're here to help you navigate our platform and answer any questions you may have",
            "Order Food:\n\n If you have Food you would like to Order, here are the steps to follow:-",
            "Create an account:" ,"Upload your listings:","Our Food price:","Find a Food:"
            ,"\nSilver Restaurant: \nIf you want to Silver Resturant from our platform, here's how to get started"
            ,""};
    String description[]={
            "",
            ""
            ,"The first step to order food and Silver Resturant is to create an account on our Application."
            ,"Once you have an account, you can start uploading personal details and your listings. Ensure that the accurate details are correct for your account which is the user base for managing the account."
            ,"Our Food price is set by default. You trust our Food. Silver Resturant app provides cheap price to order Food."
            ,"Use our search bar to find the Food you're interested in."
            ,"This app helps develop the user's easy to food order and payment"
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);

        listView=findViewById(R.id.helpbar);

        HelpAdapter helpAdapter= new HelpAdapter(Help_center.this,title,description);
        listView.setAdapter(helpAdapter);



    }
}