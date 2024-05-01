package com.vivekgroup.restaurant.Loginpro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.vivekgroup.restaurant.adapter.AboutAdapter;
import com.vivekgroup.restaurant.R;

public class About_us extends AppCompatActivity {
    ListView listView;

    String  tit[]={"Welcome to our Silver Restaurant where we specialize in the finest quality food. Restaurant khow the importance of a strong first impression. That's why they invest in exterior design, decorate their entrances and train hosts to greet guests with a warm smile.",
            "Reservation System",
            "",
            "",
            ""
    };

    String  descri[]={"",
            "Applications enable customers to book tables in advance, reducing wait times and ensuring a smoother dining experience. Restaurant staff can manage reservations more efficiently with such systems.",
            "It will also provide SMART PAYMENT options which can include Credit cards, Debitcards, etc.",
            "To accomplish cashless payments there will be a card swipe system fixed in the table. Also there will be a QR Code so that customers can do mobile payments easily",
            "Thank you for visiting our website, and we look forward to helping you discover your next favorite Food!"
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        listView=findViewById(R.id.aboutbar);

        AboutAdapter aboutAdapter= new AboutAdapter(About_us.this,tit,descri);
        listView.setAdapter(aboutAdapter);
    }
}