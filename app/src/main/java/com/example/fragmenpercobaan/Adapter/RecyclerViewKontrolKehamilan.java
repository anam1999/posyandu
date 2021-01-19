package com.example.fragmenpercobaan.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmenpercobaan.Model.Anak;
import com.example.fragmenpercobaan.Model.Kontrol_Hamil;
import com.example.fragmenpercobaan.R;

import java.util.ArrayList;

public class RecyclerViewKontrolKehamilan extends RecyclerView.Adapter<RecyclerViewKontrolKehamilan.KontrolKehamilanViewHolder> {

    ArrayList<Kontrol_Hamil> list = new ArrayList<>();

    public void sendData(ArrayList<Kontrol_Hamil> kontrol_hamils) {
        list.clear();
        list.addAll(kontrol_hamils);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewKontrolKehamilan.KontrolKehamilanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kontrolhamil, parent, false);
        return new RecyclerViewKontrolKehamilan.KontrolKehamilanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewKontrolKehamilan.KontrolKehamilanViewHolder holder, int position) {
        Kontrol_Hamil kontrolHamil = list.get(position);
        holder.name.setText(kontrolHamil.getName());
        holder.kondisi.setText(kontrolHamil.getKondisi());
        holder.jadwal.setText(kontrolHamil.getJadwal());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class KontrolKehamilanViewHolder extends RecyclerView.ViewHolder {
        TextView name, kondisi, jadwal;

        public KontrolKehamilanViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nama);
            kondisi = itemView.findViewById(R.id.kondisi_kehamilan);
            jadwal = itemView.findViewById(R.id.jadwal);

        }
    }
}