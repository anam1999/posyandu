package com.example.fragmenpercobaan.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fragmenpercobaan.R;
import com.example.fragmenpercobaan.SharedPrefManager;
import com.example.fragmenpercobaan.Url;
import com.google.zxing.Result;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

//import com.android.volley.error.AuthFailureError;
//import com.android.volley.error.VolleyError;
//import com.android.volley.request.StringRequest;

public class FragmentScanQRCode extends Fragment  implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;
    private boolean frontCamera = true;
    private boolean flashCamera = false;
    SharedPrefManager sharedPrefManager;
    ProgressDialog dialog;


    public FragmentScanQRCode(){

     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.activity_fragment_scan_q_r_code, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGroup viewGroup = view.findViewById(R.id.camera_smartphone);
        scannerView = new ZXingScannerView(getContext());
        scannerView.setResultHandler(this);

        viewGroup.addView(scannerView);


        dialog = new ProgressDialog(getContext());
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
        showDialog(true);
        scanQr(rawResult.getText());

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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.open_menu_scan_smartphone, menu);
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
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = Url.URL+"/scan-qr/34";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showDialog(false);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setTitle("Apakah ?");
//                builder.setMessage(response);
//                builder.setPositiveButton("Scan Lagi", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        resumeScan();
//                    }
//                });

//                builder.setNegativeButton("Selesai", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(getContext(), Home.class);
//                        startActivity(intent);
//                    }
//                });
//
//                builder.show();
                new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE).setTitleText(response.trim()).show();
//                new  SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE).setConfirmText("OK").show();

//                Intent intent = new Intent(getContext(), Home.class);
//                startActivity(intent);
                //Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showDialog(false);
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();

                data.put("id_posyandu", qrCode);
//                data.put("session_id", sharedPrefManager.getSpIdSession());
//                data.put("event_id", sharedPrefManager.getSpIdEvent());
//                data.put("token", sharedPrefManager.getSPToken());
                return data;
            }
        };
        queue.getCache().clear();
        queue.add(request);
    }

    private void resumeScan(){
        scannerView.resumeCameraPreview(this);
    }

    private void showDialog(Boolean state) {

        if (state) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }
}
