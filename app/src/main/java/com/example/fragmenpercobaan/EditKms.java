package com.example.fragmenpercobaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.fragmenpercobaan.Model.Anak;

public class EditKms extends AppCompatActivity {
    public final static String EXTRA_EDIT_KMS = "extra_edit";
    EditText nama,umur,suhu,tinggi,berat;
    VideoView videos;
    TextView namex;
    String id_kms;
    Button scan_kms,update_kms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kms);

        nama = findViewById(R.id.nama_kms);

        berat = findViewById(R.id.berat_kms);


        final Anak anak = getIntent().getParcelableExtra(EXTRA_EDIT_KMS);
        if (anak != null) {

            id_kms = anak.getId();
            nama.setText(anak.getName());

            namex.setText(anak.getId());

        }
        scan_kms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent scankms= new Intent(EditKms.this,ScanQRCodeDataKMS.class);
                SharedPrefManager.setIdkms(getBaseContext(),anak.getId());
                startActivity(scankms);
            }
        });

    }
}
