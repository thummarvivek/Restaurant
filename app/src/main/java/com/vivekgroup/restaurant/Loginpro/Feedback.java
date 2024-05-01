package com.vivekgroup.restaurant.Loginpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.vivekgroup.restaurant.Alljson.Feed;
import com.vivekgroup.restaurant.Alljson.ResultFeedback;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.R;

public class Feedback extends AppCompatActivity {
    RatingBar ratingBar;
    TextView texrate , texratepo;

    EditText feedtxt;
    ArrayList<Feed>arrayList;
    Button feedbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedtxt =findViewById(R.id.txtfeedback);
        feedbtn = findViewById(R.id.feedbackbtn);
        arrayList= new ArrayList<>();
        texrate=findViewById(R.id.ratetext);
        texratepo=findViewById(R.id.ratepop);
        texrate.setText("Select  the");
        texratepo.setText("rate");

        ratingBar=findViewById(R.id.rating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                if (v==1){
                    texrate.setText("Good");
                    texratepo.setText("😅");
                }
                else if(v==2){
                    texrate.setText("Very Good");
                    texratepo.setText("😀");
                }
                else if(v==3){
                    texrate.setText("Best");
                    texratepo.setText("😊");
                }
                else if(v==4){
                    texrate.setText("Awesome");
                    texratepo.setText("😎");
                }
                else if(v==5){
                    texrate.setText("Excecllent");
                    texratepo.setText("🤩");
                }
            }
        });

        feedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences= getSharedPreferences("UserData", Context.MODE_PRIVATE);
                String User_id= preferences.getString("User_id",null);
                String feedbackComments= feedtxt.getText().toString();
                String feedbackRating= texrate.getText().toString();

                APIInterface apiInterface= Appclient.getclient().create(APIInterface.class);
                Call<ResultFeedback> call=apiInterface.feedback(User_id,feedbackComments,feedbackRating);
                call.enqueue(new Callback<ResultFeedback>() {
                    @Override
                    public void onResponse(Call<ResultFeedback> call, Response<ResultFeedback> response) {
                        Toast.makeText(Feedback.this, "ThankYou For Sumbiting Feedback", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResultFeedback> call, Throwable t) {
                        Toast.makeText(Feedback.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}
