package com.blitzar.testgrability;

import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    AbsListView absListView;

    static String[] listCategories= {"Entertainment", "Games","Music","Navigation","Photo & Video","Shopping","Social Networking", "Travel","Utilities"};
    Integer[] imgCategories={R.drawable.ic_show_chart_black_24dp, R.drawable.ic_gamepad_black_24dp, R.drawable.ic_straighten_black_24dp, R.drawable.ic_navigation_black_24dp,
            R.drawable.ic_photo_size_select_actual_black_24dp, R.drawable.ic_mood_black_24dp, R.drawable.ic_group_black_24dp, R.drawable.ic_flight_black_24dp, R.drawable.ic_fullscreen_black_24dp};

    String ims;

    private ArrayList<Entry> games= new ArrayList<>();
    private ArrayList<Entry> music= new ArrayList<>();
    private ArrayList<Entry> nav= new ArrayList<>();
    private ArrayList<Entry> photo= new ArrayList<>();
    private ArrayList<Entry> shop= new ArrayList<>();
    private ArrayList<Entry> sn= new ArrayList<>();
    private ArrayList<Entry> travel= new ArrayList<>();
    private ArrayList<Entry> utilities= new ArrayList<>();
    private ArrayList<Entry> entertainment= new ArrayList<>();


    private ArrayList<Entry> entries= new ArrayList<>();
    private Entry data;
    private static String url = "https://itunes.apple.com/us/rss/topfreeapplications/limit=40/json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        //set Background for list
        int[] listItemBack= new int[] {R.drawable.fancy_list_background1, R.drawable.fancy_list_background2};

        absListView = (AbsListView) findViewById(R.id.list);

        setTitle(getResources().getText(R.string.categories_title));




        ListItemAdapter adapter = new ListItemAdapter(this, listCategories, imgCategories);
        absListView.setAdapter(adapter);

        int listItemBackPos=listCategories.length%listItemBack.length;
        absListView.setBackgroundResource(listItemBack[listItemBackPos]);
        //bring data
        new GetFeed().execute();
        absListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                evaluatePosition(position);
            }
        });
    }

    private void evaluatePosition(int position) {

        GalleryFragment galleryFragment = (GalleryFragment) getSupportFragmentManager().findFragmentByTag("galleryFragment");
        switch (position) {
            case 0:
                if (galleryFragment == null) {
                        System.out.println(entertainment);
                        callFragment(entertainment);
                    }
                break;
            case 1:
                if (galleryFragment == null) {
                        callFragment(games);
                    }
                    break;
            case 2:
                if (galleryFragment == null) {
                        callFragment(music);
                }
                break;
            case 3:
                if (galleryFragment == null) {
                        callFragment(nav);
                }
                break;
            case 4:
                if (galleryFragment == null) {
                        callFragment(photo);
                }
                break;
            case 5:
                if (galleryFragment == null) {
                        callFragment(shop);
                }
                break;
            case 6:
                if (galleryFragment == null) {
                        callFragment(sn);
                }
                break;
            case 7:
                if (galleryFragment == null) {
                        callFragment(travel);
                }
                break;
            case 8:
                if (galleryFragment == null) {
                            callFragment(utilities);
                }
                break;
                    default:
                }
        }

         class GetFeed extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... params) {
                HttpHandler httpHandler = new HttpHandler();
                //Making the request
                String jsonStr = httpHandler.makeServiceCall(url);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        //Getting object feed
                        JSONObject feed = jsonObj.getJSONObject("feed");
                        //Getting array node
                        JSONArray entry = feed.getJSONArray("entry");
                        for (int i = 0; i < entry.length(); i++) {
                            JSONObject e = entry.getJSONObject(i);
                            JSONObject name = e.getJSONObject("im:name");
                            String n = name.getString("label");
                            JSONArray image = e.getJSONArray("im:image");
                            for (int y = 0; y < image.length(); y++) {
                                JSONObject im = image.getJSONObject(y);
                                ims = im.getString("label");
                            }
                            JSONObject summary = e.getJSONObject("summary");
                            String label = summary.getString("label");
                            JSONObject category = e.getJSONObject("category");
                            JSONObject at = category.getJSONObject("attributes");
                            String term = at.getString("term");
                            data = new Entry();
                            data.setCategory(term);
                            data.setImage(ims);
                            data.setName(n);
                            data.setSummary(label);
                            Log.d("hola", term);
                            entries.add(i, data);
                            System.out.println(entries);
                        }

                    } catch (final JSONException e) {
                        String TAG = "error";
                        Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "JSON parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                System.out.println(entries);
                evaluateContent(entries);
            }
        }

    private void evaluateContent(ArrayList<Entry> entries) {
        Entry e;
        int ent=0, g=0,m=0,n=0,p=0,s=0,soc=0,t=0,u=0;
        System.out.println(entries);
        for(int i=0; i<entries.size(); i++) {
            e = entries.get(i);
            switch (e.getCategory()) {
                case "Entertainment":
                    entertainment.add(ent, e);
                    ent =+ 1;
                    break;
                case "Games":
                    games.add(g,e);
                    g =+ 1;
                    break;
                case "Music":
                    music.add(m,e);
                    m =+ 1;
                    break;
                case "Navigation":
                    nav.add(n,e);
                    n =+ 1;
                    break;
                case "Photo & Video":
                    photo.add(p,e);
                    p =+ 1;
                    break;
                case "Shopping":
                    shop.add(s,e);
                    s =+ 1;
                    break;
                case "Social Networking":
                    sn.add(soc,e);
                    soc =+ 1;
                    break;
                case "Travel":
                    travel.add(t,e);
                    t =+ 1;
                    break;
                case "Utilities":
                    Log.d("hola", e.getCategory());
                    utilities.add(u, e);
                    System.out.println(utilities);
                    u =+ 1;
                    break;
                default:
            }
        }
    }

    private void callFragment(ArrayList<Entry> entry) {
        //create parcel and add
        ArrayList<Entry> e= new ArrayList<>();
        e.addAll(entry);
        GalleryFragment galleryFragment= new GalleryFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("Entry",e);
        galleryFragment.setArguments(bundle);
        //Initiate transaction
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, galleryFragment, "galleryFragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
