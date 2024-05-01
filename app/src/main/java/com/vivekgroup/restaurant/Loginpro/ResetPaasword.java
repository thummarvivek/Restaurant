package com.vivekgroup.restaurant.Loginpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPaasword extends AppCompatActivity {

    Button reset;
    TextView regtxt;
    ArrayList<Registration>arrayList;
    EditText pass,repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_paasword);
        regtxt =findViewById(R.id.register);
        reset =findViewById(R.id.resetbtn);
        pass =findViewById(R.id.resetpassword);
        repass =findViewById(R.id.reresetpassword);
        arrayList =new ArrayList<>();

        regtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ResetPaasword.this, Registrationn.class);
                startActivity(intent);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p1 =pass.getText().toString() ;
                String p2 =repass.getText().toString();

                if (p1.equals(p2)){
                    SharedPreferences preferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    String user_id = preferences.getString("User_id",null);
                    APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
                    Call<ResultRegistration> call=apiInterface.resetpassword(p2,user_id);
                    call.enqueue(new Callback<ResultRegistration>() {
                        @Override
                        public void onResponse(Call<ResultRegistration> call, Response<ResultRegistration> response) {
                            arrayList= (ArrayList<Registration>) response.body().getRegistration();
                            Intent intent =new Intent(ResetPaasword.this, Login.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<ResultRegistration> call, Throwable t) {
                            Toast.makeText(ResetPaasword.this, "process are failed", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });
    }
}