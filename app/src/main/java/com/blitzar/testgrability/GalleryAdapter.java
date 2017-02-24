package com.blitzar.testgrability;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Entry> entry;

   public OnImageListener mListener;

    public interface OnImageListener { //create the interface
        void onItemImageClick(int pos); //create callback function
    }

    public GalleryAdapter(Context context, ArrayList<Entry> entry, OnImageListener mListener) {
        this.mListener = mListener; //receive mListener from fragment or activity
        this.context = context;
        this.entry = entry;
    }


    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.itemName.setText(entry.get(i).getName());
        Picasso.with(context).load(entry.get(i).getImage()).into(viewHolder.imageId);

        viewHolder.imageId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Use callback function in the place you want
                mListener.onItemImageClick(i);
                Log.d("msg", "is clicking");
            }
        });

    }

    @Override
    public int getItemCount() {
        return entry.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        ImageView imageId;
        public ViewHolder(View view) {
            super(view);
            itemName= (TextView)view.findViewById(R.id.itemName);
            imageId= (ImageView)view.findViewById(R.id.itemImage);
        }
    }


}
