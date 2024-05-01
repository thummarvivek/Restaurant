package com.vivekgroup.restaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vivekgroup.restaurant.R;

public class AboutAdapter extends BaseAdapter {
    Context context;
    String tit[];
    String descri[];

    public AboutAdapter(Context context, String tit[], String descri[]){
        this.context=context;
        this.tit=tit;
        this.descri=descri;
    }

    @Override
    public int getCount() {
        return tit.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=layoutInflater.inflate(R.layout.about_custom,null);

        TextView tit01 =v.findViewById(R.id.aboutitle);
        TextView descr01 =v.findViewById(R.id.aboutdescription);

        tit01.setText(tit[i]);
        descr01.setText(descri[i]);

        if (tit01.equals("")){
            tit01.setVisibility(View.GONE);
        }
        if (descr01.equals("")){
            descr01.setVisibility(View.GONE);
        }
        return v;
    }
}
