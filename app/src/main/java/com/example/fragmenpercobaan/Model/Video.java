package com.example.fragmenpercobaan.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Video implements Parcelable {

    String id,video,judul,deskripsi,pemeran,kategori;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public String getName() {
//        return name;
//    }

    public void setVideo(String video) {
        this.video = video;
    }
    public String getVideo() {
        return video;
    }


    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    public String getDeskripsi() {
        return deskripsi;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getJudul() {
        return judul;
    }

    public void setPemeran(String pemeran) {
        this.pemeran = pemeran;
    }
    public String getPemeran() {
        return pemeran;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    public String getKategori() {
        return kategori;
    }

    public Video() {
    }

    protected Video(Parcel in) {
        id = in.readString();
        video = in.readString();
        judul = in.readString();
        deskripsi = in.readString();
        pemeran = in.readString();
        kategori= in.readString();

    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(video);
        dest.writeString(judul);
        dest.writeString(deskripsi);
        dest.writeString(pemeran);
        dest.writeString(kategori);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}