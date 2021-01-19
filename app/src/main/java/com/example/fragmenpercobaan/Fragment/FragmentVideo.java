package com.example.fragmenpercobaan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.fragmenpercobaan.Activity.Chats;
import com.example.fragmenpercobaan.Activity.Imunisasi;
import com.example.fragmenpercobaan.Activity.Kb;
import com.example.fragmenpercobaan.Activity.Kms;
import com.example.fragmenpercobaan.Activity.Kontrol_Kehamilan;
import com.example.fragmenpercobaan.Adapter.RecyclerViewKms;
import com.example.fragmenpercobaan.Adapter.RecyclerViewKontrolKehamilan;
import com.example.fragmenpercobaan.MainViewModel;
import com.example.fragmenpercobaan.Model.Kontrol_Hamil;
import com.example.fragmenpercobaan.R;
import com.example.fragmenpercobaan.SharedPrefManager;

import java.util.ArrayList;

public class FragmentVideo extends Fragment {
    RecyclerView rvkms, rvkontrolhamil;
    RecyclerViewKms recyclerViewKms;

    RecyclerViewKontrolKehamilan recyclerViewKontrolKehamilan;

    SharedPrefManager sharedPrefManager;
    RequestQueue queue;
    ProgressBar loading;
    ImageView kms, kontrolhamil,kb, imunisasi,pesan;

    public FragmentVideo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        kontrolhamil = view.findViewById(R.id.iv_kontrolhamil);
        imunisasi = view.findViewById(R.id.iv_imunisasi);
        kms = view.findViewById(R.id.iv_kms);
        kb = view.findViewById(R.id.iv_kb);
        pesan = view.findViewById(R.id.pesan);

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pesan = new Intent(getContext(), Chats.class);
                startActivity(pesan);
            }
        });


        kms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kms = new Intent(getContext(), Kms.class);
                startActivity(kms);
            }
        });

        kontrolhamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kontrol = new Intent(getContext(), Kontrol_Kehamilan.class);
                startActivity(kontrol);
            }
        });

        imunisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imun = new Intent(getContext(), Imunisasi.class);
                startActivity(imun);
            }
        });

        kb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kbs = new Intent(getContext(), Kb.class);
                startActivity(kbs);
            }
        });


        queue = Volley.newRequestQueue(getContext());
        rvkms = view.findViewById(R.id.rv_sesi_rekap1);
        loading = view.findViewById(R.id.loading_rekap);
        rvkms.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewKms = new RecyclerViewKms();

        showLoading(true);

        rvkms.setAdapter(recyclerViewKms);
        rvkms.setHasFixedSize(true);
        recyclerViewKms.notifyDataSetChanged();
//ibu
        rvkontrolhamil = view.findViewById(R.id.rv_sesi_rekap2);
        loading = view.findViewById(R.id.loading_rekap);
        rvkontrolhamil.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewKontrolKehamilan = new RecyclerViewKontrolKehamilan();

        showLoading(true);

        rvkontrolhamil.setAdapter(recyclerViewKontrolKehamilan);
        rvkontrolhamil.setHasFixedSize(true);
        recyclerViewKontrolKehamilan.notifyDataSetChanged();

    }

    private void showData() {
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setKms_last(queue, getContext());
        mainViewModel.getKms_last().observe(this, new Observer<ArrayList<com.example.fragmenpercobaan.Model.Kms>>() {
            @Override
            public void onChanged(ArrayList<com.example.fragmenpercobaan.Model.Kms> eventSessions) {
                if (eventSessions != null) {
                    recyclerViewKms.sendData(eventSessions);
                    showLoading(false);
                }

            }
        });
    }

    private void showData2() {
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setKontrolhamil_last(queue, getContext());
        mainViewModel.getKontrolHamil_last().observe(this, new Observer<ArrayList<Kontrol_Hamil>>() {
            @Override
            public void onChanged(ArrayList<Kontrol_Hamil> eventSessions) {
                if (eventSessions != null) {
                    recyclerViewKontrolKehamilan.sendData(eventSessions);
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
        showData2();
    }



}
