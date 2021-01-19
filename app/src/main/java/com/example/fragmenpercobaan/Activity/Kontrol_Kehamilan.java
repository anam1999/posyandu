package com.example.fragmenpercobaan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.fragmenpercobaan.Adapter.RecyclerViewKontrolKehamilan;
import com.example.fragmenpercobaan.MainViewModel;
import com.example.fragmenpercobaan.Model.Kontrol_Hamil;
import com.example.fragmenpercobaan.R;
import com.example.fragmenpercobaan.SharedPrefManager;

import java.util.ArrayList;

public class Kontrol_Kehamilan extends AppCompatActivity {
    RecyclerView rvkontrolhamil;
    RecyclerViewKontrolKehamilan recyclerViewKontrolKehamilan;
    SharedPrefManager sharedPrefManager;
    RequestQueue queue;
    ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontrol__kehamilan);

        queue = Volley.newRequestQueue(getApplicationContext());
        rvkontrolhamil = findViewById(R.id.rv_kontrolhamils);
        loading = findViewById(R.id.loading_kontrolhamil);
        rvkontrolhamil.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewKontrolKehamilan = new RecyclerViewKontrolKehamilan();

        showLoading(true);

        rvkontrolhamil.setAdapter(recyclerViewKontrolKehamilan);
        rvkontrolhamil.setHasFixedSize(true);
        recyclerViewKontrolKehamilan.notifyDataSetChanged();
    }

    private void showData() {
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setKontrolhamil(queue, getApplicationContext());
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
