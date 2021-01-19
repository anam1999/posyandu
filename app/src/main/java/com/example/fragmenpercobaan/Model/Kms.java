package com.example.fragmenpercobaan.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Kms  implements Parcelable {

    String id, name, usia,jadwal,berat,tinggi,suhu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public String getBb() {
        return berat;
    }
    public void setBb(String berat) {
        this.berat = berat;
    }

    public String getTb() {
        return tinggi;
    }

    public void setTb(String tinggi) {
        this.tinggi = tinggi;
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }
    public String getJadwal() {
        return jadwal;
    }
    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public Kms() {
    }

    protected Kms(Parcel in) {
        id = in.readString();
        name = in.readString();
        usia = in.readString();
        berat = in.readString();
        tinggi = in.readString();
        suhu = in.readString();

    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(usia);
        dest.writeString(berat);
        dest.writeString(tinggi);
        dest.writeString(suhu);
        dest.writeString(jadwal);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Kms> CREATOR = new Creator<Kms>() {
        @Override
        public Kms createFromParcel(Parcel in) {
            return new Kms(in);
        }

        @Override
        public Kms[] newArray(int size) {
            return new Kms[size];
        }
    };
}
