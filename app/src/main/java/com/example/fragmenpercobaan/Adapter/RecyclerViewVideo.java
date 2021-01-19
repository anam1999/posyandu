package com.example.fragmenpercobaan.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmenpercobaan.Activity.Detail_Video;
import com.example.fragmenpercobaan.Model.Video;
import com.example.fragmenpercobaan.R;
import com.example.fragmenpercobaan.Url;

import java.util.ArrayList;

public class RecyclerViewVideo extends RecyclerView.Adapter<RecyclerViewVideo.RekapitulasiViewHolder> {

    ArrayList<Video> list = new ArrayList<>();

    public void sendData(ArrayList<Video> videos) {
        list.clear();
        list.addAll(videos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RekapitulasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new RekapitulasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RekapitulasiViewHolder holder, int position) {
        Video video= list.get(position);

        holder.vids.setVideoURI(Uri.parse("http://portal.posdayandu.id/uploads/video/" + video.getVideo()));
        holder.judul.setText(video.getJudul());
        holder.deskripsi.setText(video.getDeskripsi());
        holder.kategori.setText(video.getKategori());
        holder.pemeran.setText(video.getPemeran());
//        holder.video.setText(anak.getVideo());
//        Glide.with(holder.itemView.getContext())
//                .load( "http://192.168.0.109/posdayandus/img/" + anak.getVideo())
//                .apply(new RequestOptions().centerCrop())
//                .into(holder.vid);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               Video video = list.get(holder.getAdapterPosition());
                Intent intent = new Intent(holder.itemView.getContext(), Detail_Video.class);
                intent.putExtra(Detail_Video.EXTRA_VIDEO, video);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RekapitulasiViewHolder extends RecyclerView.ViewHolder {
        TextView judul,deskripsi,kategori,pemeran, video;

        VideoView vids;

        public RekapitulasiViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul);
            deskripsi = itemView.findViewById(R.id.deskripsi);
            kategori = itemView.findViewById(R.id.kategori);
            pemeran = itemView.findViewById(R.id.pemeran);
            vids = itemView.findViewById(R.id.videos);
            vids.start();

        }
    }


}
