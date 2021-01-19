package com.example.fragmenpercobaan;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefManager {
    public static final String id = "ID";
    public static final String NAME = "name";
    public static final String ID_KMS = "id_kms";
    public static final String ID_USER = "ID_USER";


    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setid(Context context, String idku){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(id, idku);
        editor.apply();
    }

    public static String getId(Context context){
        return getSharedPreference(context).getString(id,"errors");
    }
    public static void setIdkms(Context context, String id_kms){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(ID_KMS, id_kms);
        editor.apply();
    }

    public static String getIdKms(Context context){
        return getSharedPreference(context).getString(ID_KMS,"errors");
    }
}
