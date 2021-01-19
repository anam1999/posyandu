package com.example.fragmenpercobaan.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fragmenpercobaan.Activity.Detail_Video;
import com.example.fragmenpercobaan.EditKms;
import com.example.fragmenpercobaan.Model.Anak;
import com.example.fragmenpercobaan.R;

import java.util.ArrayList;

public class RecyclerViewAnak extends RecyclerView.Adapter<RecyclerViewAnak.RekapitulasiViewHolder> {

    ArrayList<Anak> list = new ArrayList<>();

    public void sendData(ArrayList<Anak> anak) {
        list.clear();
        list.addAll(anak);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RekapitulasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anak, parent, false);
        return new RekapitulasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RekapitulasiViewHolder holder, int position) {
        Anak anak = list.get(position);
        holder.name.setText(anak.getName());
//        holder.video.setText(anak.getVideo());
//        Glide.with(holder.itemView.getContext())
//                .load( "http://192.168.0.109/posdayandus/img/" + anak.getVideo())
//                .apply(new RequestOptions().centerCrop())
//                .into(holder.vid);

        holder.vids.setVideoURI(Uri.parse("http://192.168.0.109/posdayandus/img/" +anak.getVideo() ));




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Anak anak = list.get(holder.getAdapterPosition());
                Intent intent = new Intent(holder.itemView.getContext(), Detail_Video.class);
                intent.putExtra(EditKms.EXTRA_EDIT_KMS, anak);
                holder.itemView.getContext().startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RekapitulasiViewHolder extends RecyclerView.ViewHolder {
        TextView name, video;
        ImageView vid;
        VideoView vids;

        public RekapitulasiViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_anak);
            vids=itemView.findViewById(R.id.video);
            vids.start();

        }
    }


}