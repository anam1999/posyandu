package com.example.fragmenpercobaan;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fragmenpercobaan.Model.Kms;
import com.google.gson.Gson;
import com.google.zxing.Result;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

//import com.android.volley.error.AuthFailureError;
//import com.android.volley.error.VolleyError;
//import com.android.volley.request.StringRequest;

public class ScanQRCodeDataKMS extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;
    public final static String EXTRA_EDIT_KMS = "extra_edit";

    private boolean frontCamera = true;
    private boolean flashCamera = false;
    SharedPrefManager sharedPrefManager;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scan_q_r_code_data_k_m_s);

        final Kms kms = getIntent().getParcelableExtra(EXTRA_EDIT_KMS);

        ViewGroup viewGroup = findViewById(R.id.camera_smartphone);
        scannerView = new ZXingScannerView(getApplicationContext());
        scannerView.setResultHandler(this);

        viewGroup.addView(scannerView);


        dialog = new ProgressDialog(getApplicationContext());
        dialog.setMessage("Memproses...");
        dialog.setCancelable(false);
        if (!flashCamera) {
            flashCamera = true;
        } else {
            flashCamera = false;
        }


    }

    @Override
    public void handleResult(Result rawResult) {

        //Toast.makeText(getContext(), rawResult.getText(), Toast.LENGTH_SHORT).show();
//        showDialog(true);
        scanQr(rawResult.getText());


//        String scanString =rawResult.getText();
//        String[]scanArray1 = scanString.split(",");
//        ArrayList<String> scanArray2 = new ArrayList<String>();
//        for(int i=0; i< scanArray1.length; i++){
//            String[]  scanArray1Explode = scanArray1[i].split(":");
//            scanArray2.add(scanArray1Explode[i]);
//        }


    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();

        scannerView.stopCamera();
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//
//        inflater.inflate(R.menu.open_menu_scan_smartphone, menu);
//    }
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.open_menu_scan_smartphone, menu);
    //getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;

}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.change_camera_smartphone:

                if (frontCamera) {
                    scannerView.stopCamera();
                    scannerView.startCamera(1);
                    frontCamera = false;
                } else {
                    scannerView.stopCamera();
                    scannerView.startCamera(-1);
                    frontCamera = true;
                }

                break;
            case R.id.flash_camera_smartphone:

                if (flashCamera) {
                    scannerView.setFlash(true);
                    flashCamera = false;
                } else {
                    scannerView.setFlash(false);
                    flashCamera = true;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void scanQr(final String qrCode) {
        Gson  kms_convert = new Gson();
//        final Anak anak = kms_convert.fromJson(qrCode,Anak.class);
        final Kms kms_anak = kms_convert.fromJson(qrCode,Kms.class);
        final Kms kms = getIntent().getParcelableExtra(EXTRA_EDIT_KMS);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//        String url = Url.URL+"/scan-qr-kms/"+SharedPrefManager.getIdKms(getBaseContext());
        String url = Url.URL+"/scan-qr-kms/"+kms.getId();
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                new SweetAlertDialog(ScanQRCodeDataKMS.this,SweetAlertDialog.SUCCESS_TYPE).setTitleText(response.trim()).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                showDialog(false);
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();

                data.put("berat", kms_anak.getBb());
                data.put("tinggi", kms_anak.getTb());
                data.put("suhu", kms_anak.getSuhu());

                return data;
            }
        };
        queue.getCache().clear();
        queue.add(request);
    }

    private void resumeScan(){
        scannerView.resumeCameraPreview(this);
    }

//    private void showDialog(Boolean state) {
//
//        if (state) {
//            dialog.show();
//        } else {
//            dialog.dismiss();
//        }
//    }

}
