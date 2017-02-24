package com.blitzar.testgrability;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Juano on 02/02/2017.
 */
public class AppDetailAdapter extends RecyclerView.Adapter<AppDetailAdapter.ViewHolder> {
    private Entry entry;
    private Context context;

    public AppDetailAdapter(Context context, Entry entry) {
        this.entry = entry;
        this.context = context;
    }

    @Override
    public AppDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(entry.getImage()).resize(300,300).into(holder.iV);
        holder.t_Name.setText(entry.getName());
        holder.t_Cat.setText(entry.getCategory());
        holder.t_Sum.setText(entry.getSummary());

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iV;
        TextView t_Name;
        TextView t_Sum;
        TextView t_Cat;

        public ViewHolder(View view) {
            super(view);
            iV= (ImageView)view.findViewById(R.id.itemImg);
            t_Name= (TextView)view.findViewById(R.id.itemNam);
            t_Cat= (TextView)view.findViewById(R.id.itemCat);
            t_Sum= (TextView)view.findViewById(R.id.itemSummary);
        }
    }
}
