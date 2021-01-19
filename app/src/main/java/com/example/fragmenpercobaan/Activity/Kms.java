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
import com.example.fragmenpercobaan.Adapter.RecyclerViewAnak;
import com.example.fragmenpercobaan.Adapter.RecyclerViewKms;
import com.example.fragmenpercobaan.MainViewModel;
import com.example.fragmenpercobaan.Model.Anak;
import com.example.fragmenpercobaan.R;
import com.example.fragmenpercobaan.SharedPrefManager;

import java.util.ArrayList;

public class Kms extends AppCompatActivity {
    RecyclerView rvKms;
    RecyclerViewKms recyclerViewKms;
    SharedPrefManager sharedPrefManager;
    RequestQueue queue;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kms);

        queue = Volley.newRequestQueue(getApplicationContext());
        rvKms = findViewById(R.id.rv_kms);
        loading = findViewById(R.id.loading_rekap);
        rvKms.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewKms = new RecyclerViewKms();

        showLoading(true);

        rvKms.setAdapter(recyclerViewKms);
        rvKms.setHasFixedSize(true);
        recyclerViewKms.notifyDataSetChanged();
    }

    private void showData() {
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setKms(queue, getApplicationContext());
        mainViewModel.getKms().observe(this, new Observer<ArrayList<com.example.fragmenpercobaan.Model.Kms>>() {
            @Override
            public void onChanged(ArrayList<com.example.fragmenpercobaan.Model.Kms> eventSessions) {
                if (eventSessions != null) {
                    recyclerViewKms.sendData(eventSessions);
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
