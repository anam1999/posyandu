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
import com.example.fragmenpercobaan.Adapter.RecyclerViewVideo;
import com.example.fragmenpercobaan.MainViewModel;
import com.example.fragmenpercobaan.Model.Video;
import com.example.fragmenpercobaan.R;

import java.util.ArrayList;

public class FragmentVideos extends Fragment {

    RecyclerView rvVideo;
    RecyclerViewVideo recyclerViewVideo;
    RequestQueue queue;
    ProgressBar loading;

    public FragmentVideos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment_videos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(getContext());
        rvVideo = view.findViewById(R.id.rv_video);
        loading = view.findViewById(R.id.loading);
        rvVideo.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewVideo = new RecyclerViewVideo();

        showLoading(true);

        rvVideo.setAdapter(recyclerViewVideo);
        rvVideo.setHasFixedSize(true);
        recyclerViewVideo.notifyDataSetChanged();
    }

    private void showData() {
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setVideo(queue, getContext());
        mainViewModel.getvIDEO().observe(this, new Observer<ArrayList<Video>>() {
            @Override
            public void onChanged(ArrayList<Video> eventSessions) {
                if (eventSessions != null) {
                    recyclerViewVideo.sendData(eventSessions);
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
