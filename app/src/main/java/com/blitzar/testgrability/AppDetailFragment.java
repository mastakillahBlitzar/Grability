package com.blitzar.testgrability;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Juano on 02/02/2017.
 */
public class AppDetailFragment extends GalleryFragment {

    private ArrayList<Entry> entry;
    private Entry e;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle b= getArguments();
        e=b.getParcelable("Detail");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.app_detail_fragment,container,false);

        setHasOptionsMenu(true);
        initViews(rootView);

        setRetainInstance(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initViews(final ViewGroup rootView) {
        RecyclerView recyclerView= (RecyclerView)rootView.findViewById(R.id.card_recycler_details);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Entry _entry= prepareData();
        AppDetailAdapter ada = new AppDetailAdapter(getActivity(), _entry);
        recyclerView.setAdapter(ada);
    }

    private Entry prepareData(){

            Entry itemDetail = new Entry();
            itemDetail.setName(e.getName());
            itemDetail.setImage(e.getImage());
            itemDetail.setSummary(e.getSummary());
            Log.d("test",itemDetail.getSummary());
            itemDetail.setCategory(e.getCategory());

            return itemDetail;
    }
}
