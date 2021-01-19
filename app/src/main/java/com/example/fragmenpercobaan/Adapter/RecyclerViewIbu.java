package com.example.fragmenpercobaan.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmenpercobaan.Model.Anak;
import com.example.fragmenpercobaan.Model.Ibu;
import com.example.fragmenpercobaan.R;

import java.util.ArrayList;

public class RecyclerViewIbu extends RecyclerView.Adapter<RecyclerViewIbu.IbuViewHolder> {

    ArrayList<Ibu> list = new ArrayList<>();

    public void sendData(ArrayList<Ibu> ibus) {
        list.clear();
        list.addAll(ibus);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewIbu.IbuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ibu, parent, false);
        return new RecyclerViewIbu.IbuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewIbu.IbuViewHolder holder, int position) {
        Ibu ibu = list.get(position);
        holder.name.setText(ibu.getName());
        holder.status.setText(ibu.getStatus());
        holder.jumlah_anak.setText(ibu.getJumlah_anak());
        holder.nik.setText(ibu.getNik());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class IbuViewHolder extends RecyclerView.ViewHolder {
        TextView name, status, jumlah_anak,nik;

        public IbuViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_ibu);
            status = itemView.findViewById(R.id.status);
            jumlah_anak = itemView.findViewById(R.id.jumlah_anak);
            nik = itemView.findViewById(R.id.nik);
        }
    }
}