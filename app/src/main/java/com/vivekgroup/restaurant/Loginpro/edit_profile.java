package com.vivekgroup.restaurant.Loginpro;

import static android.view.View.GONE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vivekgroup.restaurant.Alljson.Registration;
import com.vivekgroup.restaurant.Alljson.ResultRegistration;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.Homepage.Homepage;
import com.vivekgroup.restaurant.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_profile extends AppCompatActivity {
    private static final String CHANNEL_ID="My Channal";
    private static final int NOTIFICATION_ID = 103;
    private EditText txt3;
    ProgressBar progressBar ,progressBar2;



    public EditText txt1,txt4,txt5;
    public final int CAMERA_PIC_REQUEST = 100;

    ArrayList<Registration>arrayList;

    String uridata;

    ImageView imageView;
    FloatingActionButton uploadbtn;
    ProgressDialog progressDialog;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        txt1=findViewById(R.id.username1);
        txt3=findViewById(R.id.Address2);
        txt4=findViewById(R.id.phone02);
        txt5=findViewById(R.id.mail02);
        btn=findViewById(R.id.profilebtn);
        progressBar =findViewById(R.id.editprogressBar);
        progressBar2 =findViewById(R.id.imgprogressBar);
        progressBar2.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        imageView = findViewById(R.id.profiledp);
        uploadbtn= findViewById(R.id.uploadbtn);
        uploadbtn.setVisibility(GONE);
        arrayList = new ArrayList<>();
        SharedPreferences Preferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String user_id = Preferences.getString("User_id",null);
        String user_name = Preferences.getString("Username",null);
        String Address = Preferences.getString("Address", null);
        String Phone_no = Preferences.getString("Phone_no", null);
        String Email = Preferences.getString("Email", null);

        txt1.setText(user_name);
        txt3.setText(Address);
        txt4.setText(Phone_no);
        txt5.setText(Email);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences Preferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                String user_id = Preferences.getString("User_id",null);
                String user_name=txt1.getText().toString();
                String Address=txt3.getText().toString();
                String Phone_no=txt4.getText().toString();
                String Email=txt5.getText().toString();

                APIInterface apiInterface= Appclient.getclient().create(APIInterface.class);
                Call<ResultRegistration> call =apiInterface.updatereg(user_name,Email,Phone_no,Address,user_id);
                call.enqueue(new Callback<ResultRegistration>() {
                    @Override
                    public void onResponse(Call<ResultRegistration> call, Response<ResultRegistration> response) {
                        Toast.makeText(edit_profile.this, "update successful", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.VISIBLE);
                        btn.setVisibility(GONE);
                        arrayList=(ArrayList<Registration>)response.body().getRegistration();
                        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sharedPreferences.edit();
                        ed.putString("User_id",arrayList.get(0).getUserId());
                        ed.putString("username",(String) arrayList.get(0).getUserName());
                        ed.putString("Address", (String) arrayList.get(0).getAddress());
                        ed.putString("Phone_no", (String) arrayList.get(0).getPhoneno());
                        ed.putString("Email", (String) arrayList.get(0).getEmail());
                        ed.putBoolean("is_regi",true);
                        ed.apply();
                        notiy();
                        Intent intent=new Intent(edit_profile.this, Homepage.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<ResultRegistration> call, Throwable t) {
                        Toast.makeText(edit_profile.this, ""+t, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(GONE);
                        btn.setVisibility(View.VISIBLE);

                    }
                });

            }



        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                String user_id= sharedPreferences.getString("User_id",null);

                try {
                    File file = new File(uridata);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part fileupload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                    RequestBody uid=RequestBody.create(MediaType.parse("text/plain"),user_id);
                    Log.e("uploadlogerr"," "+user_id);
                    APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
                    Call<ResultRegistration> call = apiInterface.upload(fileupload,uid);
                    call.enqueue(new Callback<ResultRegistration>() {
                        @Override
                        public void onResponse(Call<ResultRegistration> call, Response<ResultRegistration> response) {
                            assert response.body() != null;
                            arrayList=(ArrayList<Registration>) response.body().getRegistration();
                            uploadbtn.setVisibility(View.GONE);
                            progressBar2.setVisibility(View.VISIBLE);
                            SharedPreferences preferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                            SharedPreferences.Editor ed=preferences.edit();
                            ed.putString("Profilepicture", (String) arrayList.get(0).getProfilePicture());
                            ed.apply();
                            notimg();
//                                updateimgda();
                            Toast.makeText(edit_profile.this, "Your Profile is Upload", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResultRegistration> call, Throwable t) {
                            Toast.makeText(edit_profile.this, "Upload Fail"+t, Toast.LENGTH_SHORT).show();
                            uploadbtn.setVisibility(View.GONE);
                            progressBar2.setVisibility(GONE);
//                                updateimgda();
                            Log.e("Error",String.valueOf(t));

                        }
                    });


                }catch (Exception e){
                    Toast.makeText(edit_profile.this, "" + e, Toast.LENGTH_SHORT).show();
                    Log.e("errirore", String.valueOf(e));
                    uploadbtn.setVisibility(View.GONE);
                    progressBar2.setVisibility(GONE);
                    Toast.makeText(edit_profile.this, "ex : "+e, Toast.LENGTH_SHORT).show();


                }

            }
        });

    }



    public void notiy(){
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.smslogo, null);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

        Bitmap largeIcon = bitmapDrawable.getBitmap();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification;
        SimpleDateFormat data = new SimpleDateFormat("HH:mm");
        String currentDate = data.format(new Date());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.smslogo)
                    .setSubText(currentDate) //set title of notification
                    .setContentText("Edit Profile Are Updated")//this is notification message
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.smslogo)
                    .setSubText(currentDate) //set title of notification
                    .setContentText("Edit Profile Are Updated")//this is notification message
                    .build();


        }
        nm.notify(NOTIFICATION_ID, notification);

    }

    public void notimg(){
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.smslogo, null);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

        Bitmap largeIcon = bitmapDrawable.getBitmap();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification;
        SimpleDateFormat data = new SimpleDateFormat("HH:mm");
        String currentDate = data.format(new Date());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.smslogo)
                    .setSubText(currentDate) //set title of notification
                    .setContentText("Profile Picture Are Updated")//this is notification message
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.smslogo)
                    .setSubText(currentDate) //set title of notification
                    .setContentText("Profile Picture Are Updated")//this is notification message
                    .build();


        }
        nm.notify(NOTIFICATION_ID, notification);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            uploadbtn.setVisibility(View.VISIBLE);
            uridata= Path.getPathFromUri(getApplicationContext(),uri);
            Log.e("IMGPATH",uridata);



        }
    }
}
