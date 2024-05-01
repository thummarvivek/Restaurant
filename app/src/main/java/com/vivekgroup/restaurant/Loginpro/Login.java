package com.vivekgroup.restaurant.Loginpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vivekgroup.restaurant.Alljson.Registration;
import com.vivekgroup.restaurant.Alljson.ResultRegistration;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.Homepage.Homepage;
import com.vivekgroup.restaurant.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    TextView regaccount,forget;
    EditText ruser,rpassword;
    ArrayList<Registration>arrayList;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        regaccount =findViewById(R.id.registerac);
        forget =findViewById(R.id.forgetregister);
        loginbtn = findViewById(R.id.loginbtn);
        ruser =findViewById(R.id.loginuser);
        rpassword = findViewById(R.id.loginpass);
        arrayList=new ArrayList<>();

        SharedPreferences preferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Boolean b =preferences.getBoolean("is_regi",false);
        if(b){
            Intent intent =new Intent(Login.this,Homepage.class);
            startActivity(intent);
            finish();
        }

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login.this, Forget.class);
                startActivity(intent);

            }
        });

        regaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login.this, Registrationn.class);
                startActivity(intent);
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (b){

                    Intent intent=new Intent(Login.this,Homepage.class);
                    startActivity(intent);
                    finish();

                }
              String User_name =ruser.getText().toString();
              String Password =rpassword.getText().toString();

                APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
                Call<ResultRegistration> call=apiInterface.login(User_name,Password);
                call.enqueue(new Callback<ResultRegistration>() {
                    @Override
                    public void onResponse(Call<ResultRegistration> call, Response<ResultRegistration> response) {
                        try {
                            arrayList = (ArrayList<Registration>) response.body().getRegistration();
                            String u=arrayList.get(0).getUserName();
                            String p=arrayList.get(0).getPassword();

                        if (User_name.equals(u)&&Password.equals(p)){
                            Intent intent=new Intent(Login.this, Homepage.class);
                            startActivity(intent);
                            Toast.makeText(Login.this, "Welcome", Toast.LENGTH_SHORT).show();

                            SharedPreferences preferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                            SharedPreferences.Editor ed=preferences.edit();
                            ed.putString(User_name,u);
                            ed.putString(Password,p);
                            ed.putString("User_id", (String) arrayList.get(0).getUserId());
                            ed.putString("Username", (String) arrayList.get(0).getUserName());
                            ed.putString("Email", (String) arrayList.get(0).getEmail());
                            ed.putString("Address",(String) arrayList.get(0).getAddress());
                            ed.putString("Bio",(String) arrayList.get(0).getBio());
                            ed.putString("Dob", (String) arrayList.get(0).getDob());
                            ed.putString("Gender", (String) arrayList.get(0).getGender());
                            ed.putString("Profilepicture", (String) arrayList.get(0).getProfilePicture());
                            ed.putString("log",(String) arrayList.get(0).getLog());
                            ed.putString("lat",(String) arrayList.get(0).getLat());
                            ed.putString("Phone_no",(String) arrayList.get(0).getPhoneno());
                            ed.putString("password",(String) arrayList.get(0).getPassword());
                            ed.putBoolean("is_regi",true);
                            ed.apply();
                            finish();
                        }
                        else {
                            ruser.setText(" ");
                            rpassword.setText(" ");
                            Toast.makeText(Login.this, "please retry", Toast.LENGTH_SHORT).show();
                        }
                        }catch (Exception e){
                            Toast.makeText(Login.this, ""+e, Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResultRegistration> call, Throwable t) {

                    }
                });



            }
        });
    }
}