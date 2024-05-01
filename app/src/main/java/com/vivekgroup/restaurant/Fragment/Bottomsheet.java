package com.vivekgroup.restaurant.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.vivekgroup.restaurant.Loginpro.About_us;
import com.vivekgroup.restaurant.Loginpro.Contact_us;
import com.vivekgroup.restaurant.Loginpro.Feedback;
import com.vivekgroup.restaurant.Loginpro.Help_center;
import com.vivekgroup.restaurant.Loginpro.Login;
import com.vivekgroup.restaurant.R;


public class Bottomsheet extends BottomSheetDialogFragment {

    TextView txt001, txt002, txt003, txt004, txt005;


    public Bottomsheet() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bottomsheet, container, false);

        txt001=view.findViewById(R.id.number001);
        txt002=view.findViewById(R.id.number002);
        txt003=view.findViewById(R.id.number003);
        txt004=view.findViewById(R.id.number004);
        txt005=view.findViewById(R.id.number005);


        txt001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Feedback.class);
                startActivity(i);
            }
        });

        txt002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), About_us.class);
                startActivity(i);
            }
        });

        txt003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Contact_us.class);
                startActivity(i);
            }
        });

        txt004.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Help_center.class);
                startActivity(i);
            }

        });

        txt005.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences =getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sharedPreferences.edit();
                 Intent i = new Intent(getActivity(), Login.class);
                 startActivity(i);
                 ed.clear();
                 ed.apply();

            }
        });


        return view;
    }

}