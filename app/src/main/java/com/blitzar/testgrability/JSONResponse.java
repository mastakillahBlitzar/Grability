package com.blitzar.testgrability;

import java.util.ArrayList;

/**
 * Created by Juano on 27/01/2017.
 */
public class JSONResponse extends ArrayList<Entry> {
    private ArrayList<Entry> entry= new ArrayList<>();

    public ArrayList<Entry> getEntry() {
        return entry;
    }

    public void setEntry(ArrayList<Entry> entry) {
        this.entry = entry;
    }
}
