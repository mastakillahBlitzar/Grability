package com.blitzar.testgrability;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Juano on 04/02/2017.
 */
public class ListItemAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] catName;
    private final Integer[] imgId;
    public ListItemAdapter(Activity context, String[] catName, Integer[] imgId) {
        super(context, R.layout.list_item, catName);
        this.context = context;
        this.catName = catName;
        this.imgId = imgId;
    }

    public View getView(int pos, View v, ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item, null, true);

        TextView cat= (TextView) rowView.findViewById(R.id.cat_Name);
        ImageView _arrw= (ImageView) rowView.findViewById(R.id.arrow);

        cat.setText(catName[pos]);
        _arrw.setImageResource(imgId[pos]);
        return rowView;
    };
}
