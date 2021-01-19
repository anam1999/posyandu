package com.example.fragmenpercobaan;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.fragmenpercobaan.Model.Anak;
import com.example.fragmenpercobaan.Model.Ibu;
import com.example.fragmenpercobaan.Model.Imunisasi;
import com.example.fragmenpercobaan.Model.Kms;
import com.example.fragmenpercobaan.Model.Kontrol_Hamil;
import com.example.fragmenpercobaan.Model.MessageKeluhan;
import com.example.fragmenpercobaan.Model.Video;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import com.android.volley.error.AuthFailureError;
//import com.android.volley.error.VolleyError;
//import com.android.volley.request.JsonArrayRequest;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Anak>> listanak = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Anak>> listanak_last = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Kms>> listkms = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Kms>> kms_last = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Kontrol_Hamil>> listkontrolhamil = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Kontrol_Hamil>> listkontrolhamil_last = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Ibu>> listibu = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Imunisasi>> listimunisasi = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Video>> listvideo = new MutableLiveData<>();

    private MutableLiveData<ArrayList<MessageKeluhan>> listmessagekeluhan = new MutableLiveData<>();

    public MainViewModel() {
    }
    public void setAnak (RequestQueue queue, final Context context) {
        final ArrayList<Anak> listItemAnak = new ArrayList<>();



        String url = Url.URL+"/anak/35";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);
                        Anak anak = new Anak();
                        anak.setId(data.getString("id"));
                        anak.setName(data.getString("nama"));
                        anak.setVideo(data.getString("video"));

                        listItemAnak.add(anak);
                    }
                    listanak.postValue(listItemAnak);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                return data;
            }
        };
        queue.getCache().clear();
        queue.add(arrayRequest);
    }
    public LiveData<ArrayList<Anak>> getAnak() {
        return listanak;
    }

    public void setAnak_Last (RequestQueue queue, final Context context) {
        final ArrayList<Anak> listItemAnak = new ArrayList<>();



        String url = Url.URL+"/kms_last/34";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);
                        Anak anak = new Anak();
                        anak.setId(data.getString("id"));
                        anak.setName(data.getString("nama"));

                        listItemAnak.add(anak);
                    }
                    listanak.postValue(listItemAnak);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                return data;
            }
        };
        queue.getCache().clear();
        queue.add(arrayRequest);
    }
    public LiveData<ArrayList<Anak>> getAnak_Last() {
        return listanak;
    }


    //KMS
    public void setKms (RequestQueue queue, final Context context) {
        final ArrayList<Kms> listItemKms = new ArrayList<>();



        String url = Url.URL+"/kms/34";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);
                        Kms kms = new Kms();
                        kms.setId(data.getString("id"));
                        kms.setName(data.getString("nama"));
                        kms.setUsia(data.getString("usia"));
                        kms.setBb(data.getString("bb"));
                        kms.setTb(data.getString("tb"));
                        kms.setSuhu(data.getString("suhu"));
                        kms.setJadwal(data.getString("jadwal"));

                        listItemKms.add(kms);
                    }
                    listkms.postValue(listItemKms);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                return data;
            }
        };
        queue.getCache().clear();
        queue.add(arrayRequest);
    }
    public LiveData<ArrayList<Kms>> getKms() {
        return listkms;
    }

    public void setKms_last (RequestQueue queue, final Context context) {
        final ArrayList<Kms> listItemKms = new ArrayList<>();



        String url = Url.URL+"/kms_last/34";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);
                        Kms kms = new Kms();
                        kms.setId(data.getString("id"));
                        kms.setName(data.getString("nama"));
                        kms.setUsia(data.getString("usia"));
                        kms.setBb(data.getString("bb"));
                        kms.setTb(data.getString("tb"));
                        kms.setSuhu(data.getString("suhu"));
                        kms.setJadwal(data.getString("jadwal"));

                        listItemKms.add(kms);
                    }
                    kms_last.postValue(listItemKms);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                return data;
            }
        };
        queue.getCache().clear();
        queue.add(arrayRequest);
    }
    public LiveData<ArrayList<Kms>> getKms_last() {
        return kms_last;
    }

    //Ibu

    public void setIbu (RequestQueue queue, final Context context) {
        final ArrayList<Ibu> listItemIbu = new ArrayList<>();



        String url = Url.URL+"/show_ibu/34";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);
                        Ibu ibu = new Ibu();
                        ibu.setId(data.getString("id"));
                        ibu.setName(data.getString("name"));
                        ibu.setStatus(data.getString("status"));
                        ibu.setJumlah_anak(data.getString("jumlah_anak"));
                        ibu.setNik(data.getString("nik"));

                        listItemIbu.add(ibu);
                    }
                    listibu.postValue(listItemIbu);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                return data;
            }
        };
        queue.getCache().clear();
        queue.add(arrayRequest);
    }
    public LiveData<ArrayList<Ibu>> getIbu() {
        return listibu;
    }

    //kontrolhamil
    public void setKontrolhamil (RequestQueue queue, final Context context) {
        final ArrayList<Kontrol_Hamil> listItemkontrolHamil = new ArrayList<>();

        String url = Url.URL+"/kontrolkehamilan/34";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);
                        Kontrol_Hamil kontrolHamil = new Kontrol_Hamil();
                        kontrolHamil.setId(data.getString("id"));
                        kontrolHamil.setName(data.getString("nama"));
                        kontrolHamil.setKondisi(data.getString("kondisi_kehamilan"));
                        kontrolHamil.setJadwal(data.getString("jadwal"));

                        listItemkontrolHamil.add(kontrolHamil);
                    }
                    listkontrolhamil.postValue(listItemkontrolHamil);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                return data;
            }
        };
        queue.getCache().clear();
        queue.add(arrayRequest);
    }
    public LiveData<ArrayList<Kontrol_Hamil>> getKontrolHamil() {
        return listkontrolhamil;
    }

    public void setKontrolhamil_last (RequestQueue queue, final Context context) {
        final ArrayList<Kontrol_Hamil> listItemkontrolHamil = new ArrayList<>();

        String url = Url.URL+"/kontrolkehamilan_last/34";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);
                        Kontrol_Hamil kontrolHamil = new Kontrol_Hamil();
                        kontrolHamil.setId(data.getString("id"));
                        kontrolHamil.setName(data.getString("nama"));
                        kontrolHamil.setKondisi(data.getString("kondisi_kehamilan"));
                        kontrolHamil.setJadwal(data.getString("jadwal"));

                        listItemkontrolHamil.add(kontrolHamil);
                    }
                    listkontrolhamil_last.postValue(listItemkontrolHamil);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                return data;
            }
        };
        queue.getCache().clear();
        queue.add(arrayRequest);
    }
    public LiveData<ArrayList<Kontrol_Hamil>> getKontrolHamil_last() {
        return listkontrolhamil_last;
    }




    public void setImunisasi (RequestQueue queue, final Context context) {
        final ArrayList<Imunisasi> listItemImunisasi = new ArrayList<>();



        String url = Url.URL+"/imunisasi/34";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);
                        Imunisasi imunisasi = new Imunisasi();
                        imunisasi.setId(data.getString("id"));
                        imunisasi.setName(data.getString("nama"));
                        imunisasi.setNama_vaksin(data.getString("nama_vaksin"));
                        imunisasi.setJadwal(data.getString("jadwal"));

                        listItemImunisasi.add(imunisasi);
                    }
                    listimunisasi.postValue(listItemImunisasi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                return data;
            }
        };
        queue.getCache().clear();
        queue.add(arrayRequest);
    }
    public LiveData<ArrayList<Imunisasi>> getImunisasi() {
        return listimunisasi;
    }

    public void setVideo (RequestQueue queue, final Context context) {
        final ArrayList<Video> listItemVideo = new ArrayList<>();



        String url = Url.URL+"/video";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);
                        Video video = new Video();
                        video.setId(data.getString("id"));
                        video.setVideo(data.getString("nama_video"));
                        video.setJudul(data.getString("judul"));
                        video.setDeskripsi(data.getString("deskripsi"));
                        video.setKategori(data.getString("kategori"));
                        video.setPemeran(data.getString("pemeran"));


                        listItemVideo.add(video);
                    }
                    listvideo.postValue(listItemVideo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                return data;
            }
        };
        queue.getCache().clear();
        queue.add(arrayRequest);
    }
    public LiveData<ArrayList<Video>> getvIDEO() {
        return listvideo;
    }

    public void setMessagekeluhan (RequestQueue queue, final Context context) {
        final ArrayList<MessageKeluhan> listItemMessagekeluhan = new ArrayList<>();



        String url = Url.URL+"/message/32";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);
                        MessageKeluhan messageKeluhan = new MessageKeluhan();
                        messageKeluhan.setId(data.getString("id"));
                        messageKeluhan.setDari(data.getString("dari"));
                        messageKeluhan.setKe(data.getString("ke"));
                        messageKeluhan.setPesan(data.getString("pesan"));

                        listItemMessagekeluhan.add(messageKeluhan);
                    }
                    listmessagekeluhan.postValue(listItemMessagekeluhan);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                return data;
            }
        };
        queue.getCache().clear();
        queue.add(arrayRequest);
    }
    public LiveData<ArrayList<MessageKeluhan>> getMessagekeluhan() {
        return listmessagekeluhan;
    }



}