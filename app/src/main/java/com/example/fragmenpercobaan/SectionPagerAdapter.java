package com.example.fragmenpercobaan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fragmenpercobaan.Fragment.FragmentAnak;
import com.example.fragmenpercobaan.Fragment.FragmentImunisasi;
import com.example.fragmenpercobaan.Fragment.FragmentKontrolHamil;
import com.example.fragmenpercobaan.Fragment.PaketFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {
    private Context context;

    public SectionPagerAdapter(@NonNull FragmentManager fm, Context mContext) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        context = mContext;
    }

    @StringRes
    private int[] TAB_TITLES = new int[]{
            R.string.tab_1,
            R.string.tab_2


    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0 :
                fragment = new FragmentKontrolHamil();
                break;
            case 1 :
                fragment= new FragmentImunisasi();


        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}