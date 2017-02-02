package com.blitzar.testgrability;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Juano on 27/01/2017.
 */
public class Entry implements Parcelable {
    private String name;
    private String image;
    private String summary;
    private String category;

    Entry(Parcel in) {
        this.name = in.readString();
        this.image = in.readString();
        this.summary = in.readString();
        this.category = in.readString();
    }

    public Entry() {
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }


    public void setCategory(String category) {
        this.category = category;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(summary);
        dest.writeString(category);
    }

    static final Parcelable.Creator<Entry> CREATOR
            =new Parcelable.Creator<Entry>() {

        @Override
        public Entry createFromParcel(Parcel source) {
            return new Entry(source);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }

    };


}
