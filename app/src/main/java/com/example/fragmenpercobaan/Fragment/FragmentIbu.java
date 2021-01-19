package com.example.fragmenpercobaan.Fragment;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;

import com.android.volley.toolbox.Volley;
import com.example.fragmenpercobaan.Adapter.RecyclerViewAnak;
import com.example.fragmenpercobaan.Adapter.RecyclerViewIbu;

import com.example.fragmenpercobaan.MainViewModel;
import com.example.fragmenpercobaan.Model.Anak;
import com.example.fragmenpercobaan.Model.Ibu;
import com.example.fragmenpercobaan.R;
import com.example.fragmenpercobaan.ScanQRCodeDataKMS;
import com.example.fragmenpercobaan.SharedPrefManager;

import java.util.ArrayList;

public class FragmentIbu extends Fragment {

    RecyclerView rvIbu;
    RecyclerViewIbu recyclerViewIbu;
    SharedPrefManager sharedPrefManager;
    RequestQueue queue;
    ProgressBar loading;
    Button scankms;

    public FragmentIbu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment_ibu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(getContext());
        rvIbu = view.findViewById(R.id.rv_ibu);
        loading = view.findViewById(R.id.loading_ibu);
        rvIbu.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewIbu = new RecyclerViewIbu();
        scankms = view.findViewById(R.id.scankms);

        scankms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scankms = new Intent(getContext(), ScanQRCodeDataKMS.class);
                startActivity(scankms);
            }
        });

        showLoading(true);

        rvIbu.setAdapter(recyclerViewIbu);
        rvIbu.setHasFixedSize(true);
        recyclerViewIbu.notifyDataSetChanged();
    }

    private void showData() {
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setIbu(queue, getContext());
        mainViewModel.getIbu().observe(this, new Observer<ArrayList<Ibu>>() {
            @Override
            public void onChanged(ArrayList<Ibu> eventSessions) {
                if (eventSessions != null) {
                    recyclerViewIbu.sendData(eventSessions);
                    showLoading(false);
                }

            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            loading.setVisibility(View.VISIBLE);
        } else {
            loading.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }


}
