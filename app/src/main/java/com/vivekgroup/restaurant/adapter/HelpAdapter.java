package com.vivekgroup.restaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vivekgroup.restaurant.R;

public class HelpAdapter extends BaseAdapter {
    Context context;

    String title[];
    String description[];

    public HelpAdapter(Context context, String title[], String description[]){
        this.context=context;
        this.title=title;
        this.description=description;
    }


    @Override
    public int getCount() {
        return title.length;
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
        View v=layoutInflater.inflate(R.layout.help_custom,null);

        TextView tit =v.findViewById(R.id.helptitle);
        TextView desc=v.findViewById(R.id.helpdesc);

        tit.setText(title[i]);
        desc.setText(description[i]);

        if(tit.equals("")){
            tit.setVisibility(View.GONE);
        }
        if(desc.equals("")){
            desc.setVisibility(View.GONE);
        }

        return  v;
    }
}
