package com.example.fragmenpercobaan.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.fragmenpercobaan.Model.Anak;
import com.example.fragmenpercobaan.MainViewModel;
import com.example.fragmenpercobaan.R;
import com.example.fragmenpercobaan.Adapter.RecyclerViewAnak;
import com.example.fragmenpercobaan.SharedPrefManager;

import java.util.ArrayList;

public class FragmentAnak extends Fragment {
    RecyclerView rvAnak;
    RecyclerViewAnak recyclerViewAnak;
    SharedPrefManager sharedPrefManager;
    RequestQueue queue;
    ProgressBar loading;

    public FragmentAnak() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment_anak, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(getContext());
        rvAnak = view.findViewById(R.id.rv_sesi_rekap);
        loading = view.findViewById(R.id.loading_rekap);
        rvAnak.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewAnak = new RecyclerViewAnak();

        showLoading(true);

        rvAnak.setAdapter(recyclerViewAnak);
        rvAnak.setHasFixedSize(true);
        recyclerViewAnak.notifyDataSetChanged();
    }

    private void showData() {
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setAnak(queue, getContext());
        mainViewModel.getAnak().observe(this, new Observer<ArrayList<Anak>>() {
            @Override
            public void onChanged(ArrayList<Anak> eventSessions) {
                if (eventSessions != null) {
                    recyclerViewAnak.sendData(eventSessions);
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
