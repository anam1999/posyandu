package com.example.fragmenpercobaan.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmenpercobaan.Model.Anak;
import com.example.fragmenpercobaan.Model.Imunisasi;
import com.example.fragmenpercobaan.R;

import java.util.ArrayList;

public class RecyclerViewImunisasi extends RecyclerView.Adapter<RecyclerViewImunisasi.ImunisasiViewHolder> {

    ArrayList<Imunisasi> list = new ArrayList<>();

    public void sendData(ArrayList<Imunisasi> imunisasis) {
        list.clear();
        list.addAll(imunisasis);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewImunisasi.ImunisasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imunisasi, parent, false);
        return new RecyclerViewImunisasi.ImunisasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewImunisasi.ImunisasiViewHolder holder, int position) {
       Imunisasi imunisasi = list.get(position);
        holder.name.setText(imunisasi.getName());
        holder.nama_vaksin.setText(imunisasi.getNama_vaksin());
        holder.jadwal.setText(imunisasi.getJadwal());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImunisasiViewHolder extends RecyclerView.ViewHolder {
        TextView name, nama_vaksin, jadwal;

        public ImunisasiViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_anak_imun);
            nama_vaksin = itemView.findViewById(R.id.nama_vaksin);
            jadwal = itemView.findViewById(R.id.jadwal_anak);

        }
    }
}