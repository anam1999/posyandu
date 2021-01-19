package com.example.fragmenpercobaan.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmenpercobaan.R;
import com.example.fragmenpercobaan.SectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class FragmentBeranda extends Fragment {

    public FragmentBeranda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment_beranda, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getChildFragmentManager(), getContext());
        ViewPager viewPager = view.findViewById(R.id.view_pager_panitia);
        viewPager.setAdapter(sectionPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_panitia);
        tabLayout.setupWithViewPager(viewPager);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setElevation(0);

    }
}
