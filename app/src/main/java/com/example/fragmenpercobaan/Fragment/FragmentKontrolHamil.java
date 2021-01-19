package com.example.fragmenpercobaan.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.fragmenpercobaan.Adapter.RecyclerViewAnak;
import com.example.fragmenpercobaan.Adapter.RecyclerViewKontrolKehamilan;
import com.example.fragmenpercobaan.MainViewModel;
import com.example.fragmenpercobaan.Model.Anak;
import com.example.fragmenpercobaan.Model.Kontrol_Hamil;
import com.example.fragmenpercobaan.R;
import com.example.fragmenpercobaan.SharedPrefManager;

import java.util.ArrayList;

public class FragmentKontrolHamil extends Fragment {
    RecyclerView rvkontrolhamil;
    RecyclerViewKontrolKehamilan recyclerViewKontrolKehamilan;
    SharedPrefManager sharedPrefManager;
    RequestQueue queue;
    ProgressBar loading;
    public FragmentKontrolHamil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment_kontrol_hamil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(getContext());
        rvkontrolhamil = view.findViewById(R.id.rv_kontrolhamil);
        loading = view.findViewById(R.id.loading_kontrolhamil);
        rvkontrolhamil.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewKontrolKehamilan = new RecyclerViewKontrolKehamilan();

        showLoading(true);

        rvkontrolhamil.setAdapter(recyclerViewKontrolKehamilan);
        rvkontrolhamil.setHasFixedSize(true);
        recyclerViewKontrolKehamilan.notifyDataSetChanged();
    }

    private void showData() {
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setKontrolhamil(queue, getContext());
        mainViewModel.getKontrolHamil().observe(this, new Observer<ArrayList<Kontrol_Hamil>>() {
            @Override
            public void onChanged(ArrayList<Kontrol_Hamil> kontrol_hamils) {
                if (kontrol_hamils != null) {
                    recyclerViewKontrolKehamilan.sendData(kontrol_hamils);
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
