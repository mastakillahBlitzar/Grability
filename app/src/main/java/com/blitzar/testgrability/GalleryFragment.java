package com.blitzar.testgrability;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Juano on 16/01/2017.
 */
public class GalleryFragment extends Fragment {


    private final String[] itemName= {"english","lkj","jhk",
                                        "gogo", "hsdfsa", "gsdfss",
                                        "english","lkj","jhkj",
                                        "gogo", "hsdfsa", "gsdfss",
                                        "english","lkj","jhkj","gogo", "hsdfsa", "gsdfss","english","lkj","jhkj","gogo", "hsdfsa", "gsdfss"};
    private final String[] imageId= {"english","lkj","jhk",
            "gogo", "hsdfsa", "gsdfss",
            "english","lkj","jhkj",
            "gogo", "hsdfsa", "gsdfss",
            "english","lkj","jhkj","gogo", "hsdfsa", "gsdfss","english","lkj","jhkj","gogo", "hsdfsa", "gsdfss"};

    private ArrayList<Entry> entry;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the fragment layout file
        Bundle bundle= getActivity().getIntent().getExtras();
        entry= bundle.getParcelableArrayList("Entry");
        Log.d("f", String.valueOf(entry));
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.gallery_fragment_layout,container,false);
        initViews(rootView);
        setRetainInstance(true);
        return rootView;
    }

    private void initViews(ViewGroup rootView) {
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Entry> entry = prepareData();
        GalleryAdapter galleryAdapter = new GalleryAdapter(getActivity(), entry);
        recyclerView.setAdapter(galleryAdapter);
    }

    private ArrayList<Entry> prepareData(){

        ArrayList<Entry> entry = new ArrayList<>();
        for(int i=0;i<itemName.length;i++){
            Entry item_i = new Entry();
            item_i.setName(itemName[i]);
            item_i.setImage(imageId[i]);
            entry.add(item_i);
        }
        return entry;
    }


}
