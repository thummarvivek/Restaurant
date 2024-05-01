package com.vivekgroup.restaurant.Loginpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vivekgroup.restaurant.Alljson.ResultRegistration;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registrationn extends AppCompatActivity {

    EditText reguser,regphone,regemail,regpassword;
    Button regsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        reguser =findViewById(R.id.registerusername);
        regphone =findViewById(R.id.registerphoneno);
        regemail =findViewById(R.id.registeremail);
        regpassword =findViewById(R.id.registerrepassword);
        regsubmit =findViewById(R.id.registerbtn);

        regsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user =reguser.getText().toString();
                String phone =regphone.getText().toString();
                String email =regemail.getText().toString();
                String password =regpassword.getText().toString();

                APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
                Call<ResultRegistration> call=apiInterface.Register_Data(user,phone,email,password);
                call.enqueue(new Callback<ResultRegistration>() {
                    @Override
                    public void onResponse(Call<ResultRegistration> call, Response<ResultRegistration> response) {
                        Toast.makeText(Registrationn.this, "Registration have been successful", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<ResultRegistration> call, Throwable t) {
                        Toast.makeText(Registrationn.this, "Registration failed"+t, Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });


    }
}