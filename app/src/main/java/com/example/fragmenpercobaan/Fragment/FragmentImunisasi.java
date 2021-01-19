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
import com.example.fragmenpercobaan.Adapter.RecyclerViewIbu;
import com.example.fragmenpercobaan.Adapter.RecyclerViewImunisasi;
import com.example.fragmenpercobaan.MainViewModel;
import com.example.fragmenpercobaan.Model.Anak;
import com.example.fragmenpercobaan.Model.Imunisasi;
import com.example.fragmenpercobaan.R;
import com.example.fragmenpercobaan.SharedPrefManager;

import java.util.ArrayList;

public class FragmentImunisasi extends Fragment {

    RecyclerView rvImunisasi;
    RecyclerViewImunisasi recyclerViewImunisasi;
    SharedPrefManager sharedPrefManager;
    RequestQueue queue;
    ProgressBar loading;

    public FragmentImunisasi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment_imunisasi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(getContext());
        rvImunisasi = view.findViewById(R.id.rv_imunisasi);
        loading = view.findViewById(R.id.loading_imunisasi);
        rvImunisasi.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewImunisasi = new RecyclerViewImunisasi();

        showLoading(true);

        rvImunisasi.setAdapter(recyclerViewImunisasi);
        rvImunisasi.setHasFixedSize(true);
        recyclerViewImunisasi.notifyDataSetChanged();
    }

    private void showData() {
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setImunisasi(queue, getContext());
        mainViewModel.getImunisasi().observe(this, new Observer<ArrayList<Imunisasi>>() {
            @Override
            public void onChanged(ArrayList<Imunisasi> eventSessions) {
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
