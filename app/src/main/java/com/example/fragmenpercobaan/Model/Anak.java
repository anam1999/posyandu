package com.example.fragmenpercobaan.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Anak implements Parcelable {

    String id, name,video;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setVideo(String video) {
        this.video = video;
    }
    public String getVideo() {
        return video;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Anak() {
    }

    protected Anak(Parcel in) {
        id = in.readString();
        name = in.readString();
        video = in.readString();

    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(video);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Anak> CREATOR = new Creator<Anak>() {
        @Override
        public Anak createFromParcel(Parcel in) {
            return new Anak(in);
        }

        @Override
        public Anak[] newArray(int size) {
            return new Anak[size];
        }
    };
}