package com.vivekgroup.restaurant.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.vivekgroup.restaurant.Alljson.Registration;
import com.vivekgroup.restaurant.Alljson.ResultRegistration;
import com.vivekgroup.restaurant.Apifile.APIInterface;
import com.vivekgroup.restaurant.Apifile.Appclient;
import com.vivekgroup.restaurant.Loginpro.Feedback;
import com.vivekgroup.restaurant.Loginpro.edit_profile;
import com.vivekgroup.restaurant.R;
import com.vivekgroup.restaurant.shopping.Myordersh;
import com.vivekgroup.restaurant.shopping.Tableshow;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Profile extends Fragment {
    private static final int PICK_IMAGE=1;

    ArrayList<Registration>arrayList;

    Toolbar toolbar;
    ImageView imageView;
    Vibrator vibrator;
    TextView textView ,textViewedit,feedb;
    String ImageUriData;

    FloatingActionButton uploadbtn;


    public Profile(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup    container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        toolbar =view.findViewById(R.id.toolbarprofile);
        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();
        arrayList=new ArrayList<>();
        feedb =view.findViewById(R.id.scu002);
        textViewedit =view.findViewById(R.id.editpofilebtn);

        //
        TextView textView0 = view.findViewById(R.id.table);
        textView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Tableshow.class);
                startActivity(intent);

            }
        });

        feedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bottomsheet b =new Bottomsheet();
                b.show(getActivity().getSupportFragmentManager(),"tag");
            }
        });



        TextView textView1 = view.findViewById(R.id.myorders);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent( getActivity(), Myordersh.class);
                startActivity(inext);
            }
        });



        appCompatActivity.setSupportActionBar(toolbar);



        imageView=view.findViewById(R.id.profiledp);




        textView=view.findViewById(R.id.editpofilebtn);

        SharedPreferences preferences = appCompatActivity.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String user_name =preferences.getString("Username",null);
        String password=preferences.getString("password",null);
        APIInterface apiInterface = Appclient.getclient().create(APIInterface.class);
        Call<ResultRegistration> call=apiInterface.login(user_name,password);
        call.enqueue(new Callback<ResultRegistration>() {
            @Override
            public void onResponse(Call<ResultRegistration> call, Response<ResultRegistration> response) {
                try {
                    arrayList= (ArrayList<Registration>) response.body().getRegistration();

                    TextView useridfind=view.findViewById(R.id.usernametext);
                    useridfind.setText(arrayList.get(0).getUserName());

                    TextView useraddressfind=view.findViewById(R.id.useraddresstext);
                    useraddressfind.setText((CharSequence) arrayList.get(0).getAddress());

                    TextView userphonefind=view.findViewById(R.id.userphonenutext);
                    userphonefind.setText((CharSequence) arrayList.get(0).getPhoneno());

                    TextView usermailfind=view.findViewById(R.id.usermailtext);
                    usermailfind.setText(arrayList.get(0).getEmail());

                    Picasso.get().load((String) arrayList.get(0).getProfilePicture()).placeholder(R.drawable.userdp).into(imageView);



                }catch (Exception e){
                    Toast.makeText(appCompatActivity, ""+e, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResultRegistration> call, Throwable t) {

            }
        });







        //end save data




         textViewedit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                Intent intent=new Intent(getContext(), edit_profile.class);
                startActivity(intent);
             }
         });

        vibrator =(Vibrator)  getContext().getSystemService(Context.VIBRATOR_SERVICE);

   imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,100);

                vibrator.vibrate(100);

                return false;
            }
        });



        uploadbtn = view.findViewById(R.id.uploadbuttton);
        uploadbtn.setVisibility(View.GONE);
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog=new ProgressDialog(getContext());
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                SharedPreferences pre= appCompatActivity.getSharedPreferences("UserData",Context.MODE_PRIVATE);
                String userid =pre.getString("userid",null);

                try {

                    File file=new File(ImageUriData);

                    RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);

                    MultipartBody.Part fileupload = MultipartBody.Part.createFormData("file",file.getName(),requestBody);

                    RequestBody uid=RequestBody.create(MediaType.parse("text/plain"),userid);

                    Log.e("uploadlogerr", " "+userid);

                        progressDialog.dismiss();





                }catch (Exception e){
                    Toast.makeText(appCompatActivity, ""+e, Toast.LENGTH_LONG).show();
                    Log.e("errirore", String.valueOf(e));
                    Toast.makeText(appCompatActivity, "ex : "+e, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });

        return view;
    }

}//end main function