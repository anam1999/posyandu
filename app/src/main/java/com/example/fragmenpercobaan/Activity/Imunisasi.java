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
import com.example.fragmenpercobaan.Adapter.RecyclerViewImunisasi;
import com.example.fragmenpercobaan.MainViewModel;
import com.example.fragmenpercobaan.R;
import com.example.fragmenpercobaan.SharedPrefManager;

import java.util.ArrayList;

public class Imunisasi extends AppCompatActivity {
    RecyclerView rvImunisasi;
    RecyclerViewImunisasi recyclerViewImunisasi;
    SharedPrefManager sharedPrefManager;
    RequestQueue queue;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imunisasi);
        queue = Volley.newRequestQueue(getApplicationContext());
        rvImunisasi = findViewById(R.id.rv_imunisasis);
        loading = findViewById(R.id.loading_imunisasi);
        rvImunisasi.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewImunisasi = new RecyclerViewImunisasi();

        showLoading(true);

        rvImunisasi.setAdapter(recyclerViewImunisasi);
        rvImunisasi.setHasFixedSize(true);
        recyclerViewImunisasi.notifyDataSetChanged();
    }

    private void showData() {
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setImunisasi(queue, getApplicationContext());
        mainViewModel.getImunisasi().observe(this, new Observer<ArrayList<com.example.fragmenpercobaan.Model.Imunisasi>>() {
            @Override
            public void onChanged(ArrayList<com.example.fragmenpercobaan.Model.Imunisasi> eventSessions) {
                if (eventSessions != null) {
                    recyclerViewImunisasi.sendData(eventSessions);
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
