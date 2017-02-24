package com.blitzar.testgrability;

import android.support.v4.app.FragmentManager;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Juano on 16/01/2017.
 */
public class GalleryFragment extends Fragment {

    private ArrayList<Entry> entry;
    private Entry[] e;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Get the ArrayList Entry parcelable
        Bundle bundle = getArguments();
        this.entry= bundle.getParcelableArrayList("Entry");
        e=new Entry[this.entry.size()];
        System.out.println(this.entry);
        //Inflate the fragment layout file
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.gallery_fragment_layout,container,false);
        setMenuVisibility(true);
        initViews(rootView);
        setRetainInstance(true);
        return rootView;
    }

    private void initViews(final ViewGroup rootView) {
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        final ArrayList<Entry> _e = prepareData();
        //Set clickable image
        GalleryAdapter galleryAdapter = new GalleryAdapter(getActivity(), _e, new GalleryAdapter.OnImageListener() {
            @Override
            public void onItemImageClick(int pos) {
                Log.d("testing", "now is working?");
                Entry mEntry=new Entry();
                mEntry= _e.get(pos);
                Log.d("test2",mEntry.getCategory());
                callFragment(mEntry);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(galleryAdapter);
    }

    private ArrayList<Entry> prepareData(){
        for(int x=0; x<entry.size();x++) {
            e[x]=entry.get(x);
            Log.d("testing",e[x].getName());
        }
        ArrayList<Entry> x_ent = new ArrayList<>();
        for(int i=0;i<e.length;i++){
            Entry item_i = new Entry();
            item_i.setName(e[i].getName());
            item_i.setImage(e[i].getImage());
            item_i.setCategory(e[i].getCategory());
            item_i.setSummary(e[i].getSummary());
            x_ent.add(item_i);
        }
        return x_ent;
    }


    private void callFragment(Entry entry) {
        //create parcel and add
        Entry e= new Entry();
        e=entry;
        AppDetailFragment ada= new AppDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Detail", e);
        ada.setArguments(bundle);
        //Initiate transaction
        FragmentManager fm= getFragmentManager();
        FragmentTransaction transaction= fm.beginTransaction();
        transaction.replace(android.R.id.content, ada, "DetailsFragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
