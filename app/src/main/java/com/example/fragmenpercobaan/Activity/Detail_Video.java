package com.example.fragmenpercobaan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.fragmenpercobaan.Model.Anak;
import com.example.fragmenpercobaan.Model.Video;
import com.example.fragmenpercobaan.R;

public class Detail_Video extends AppCompatActivity {
    public final static String EXTRA_VIDEO = "extra";
    EditText judul,deskripsi,kategori,pemeran;
    VideoView videos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__video);

        judul = findViewById(R.id.juduls);
        deskripsi = findViewById(R.id.deskripsis);
        kategori = findViewById(R.id.kategoris);
        pemeran = findViewById(R.id.pemerans);

        videos = findViewById(R.id.detailvideo);
//        videos.start();
        final Video video = getIntent().getParcelableExtra(EXTRA_VIDEO);
        if (video != null) {

            judul.setText(video.getJudul());
            deskripsi.setText(video.getDeskripsi());
            kategori.setText(video.getKategori());
            pemeran.setText(video.getPemeran());

            videos.setVideoURI(Uri.parse("http://portal.posdayandu.id/uploads/video/" +video.getVideo() ));

            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videos);
            videos.setMediaController(mediaController);

            videos.start();




        }


    }
}
