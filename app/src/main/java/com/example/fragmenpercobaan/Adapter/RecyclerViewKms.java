package com.example.fragmenpercobaan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmenpercobaan.EditKms;
import com.example.fragmenpercobaan.Model.Kms;
import com.example.fragmenpercobaan.R;
import com.example.fragmenpercobaan.ScanQRCodeDataKMS;
import com.example.fragmenpercobaan.SharedPrefManager;

import java.util.ArrayList;

public class RecyclerViewKms  extends RecyclerView.Adapter<RecyclerViewKms.RekapitulasiViewHolder> {

    ArrayList<Kms> list = new ArrayList<>();

    public void sendData(ArrayList<Kms> kms) {
        list.clear();
        list.addAll(kms);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RekapitulasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kms, parent, false);
        return new RekapitulasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RekapitulasiViewHolder holder, int position) {
        Kms kms = list.get(position);
        holder.name.setText(kms.getName());
        holder.usia.setText(kms.getUsia());
        holder.bb.setText(kms.getBb());
        holder.tb.setText(kms.getTb());
        holder.suhu.setText(kms.getSuhu());
        holder.jadwal.setText(kms.getJadwal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Kms kms = list.get(holder.getAdapterPosition());
                Intent intent = new Intent(holder.itemView.getContext(), ScanQRCodeDataKMS.class);
                intent.putExtra(ScanQRCodeDataKMS.EXTRA_EDIT_KMS,kms);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RekapitulasiViewHolder extends RecyclerView.ViewHolder {
        TextView name, usia, jadwal,bb,tb,suhu;

        public RekapitulasiViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_anak);
            usia = itemView.findViewById(R.id.usia_anak);
            jadwal = itemView.findViewById(R.id.jadwal_anak);
            bb = itemView.findViewById(R.id.bb);
            tb = itemView.findViewById(R.id.tb);
            suhu = itemView.findViewById(R.id.suhu);
        }
    }
}
