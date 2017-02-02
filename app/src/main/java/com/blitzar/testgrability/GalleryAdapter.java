package com.blitzar.testgrability;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Entry> entry;
    //private final String[] itemName;
    //private final int[] imageId;


    public GalleryAdapter(Context context, ArrayList<Entry> entry) {
        this.context = context;
        this.entry = entry;
    }


    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemName.setText(entry.get(i).getName());
        Picasso.with(context).load(entry.get(i).getImage()).resize(120, 60).into(viewHolder.imageId);

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
