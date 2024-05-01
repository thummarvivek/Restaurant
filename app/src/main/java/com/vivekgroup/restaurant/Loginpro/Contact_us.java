package com.vivekgroup.restaurant.Loginpro;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vivekgroup.restaurant.R;


public class Contact_us extends AppCompatActivity {
    TextView tex1, tex2, tex3, tex4, tex5;
    Context contexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        tex1 = findViewById(R.id.foface);
        tex2 = findViewById(R.id.fotwit);
        tex3 = findViewById(R.id.foinsta);
        tex4 = findViewById(R.id.foyou);
        tex5 = findViewById(R.id.foplay);

        tex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setPackage("com.facebook.android");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Contact_us.this, "facebook Not Installed!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        tex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setPackage("com.twitter.android");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Contact_us.this, "twitter Not Installed!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        tex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setPackage("com.instagram.android");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Contact_us.this, "Instagram Not Installed!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        tex4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setPackage("com.google.android.youtube");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Contact_us.this, "youtube Not Installed!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        tex5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setPackage("com.android.vending");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Contact_us.this, "play store Not Installed!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}