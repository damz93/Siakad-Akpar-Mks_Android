package com.example.siaka_akpar_makassar;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
@SuppressLint("ShowToast")
public class Act_dosen extends Activity {
 ListView lv;
 ProgressDialog pDialog;
 JSONArray contacts = null;
 public int isi;
 public String isiiii,kode;
 
 
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.tamp_inf);
        lv=(ListView)findViewById(R.id.list);       
        new AmbilData().execute();
  }
    
 public class AmbilData extends AsyncTask<String, String, String> {
   
  ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
  String id, judul, waktu;

  @Override
  protected void onPreExecute() {
   super.onPreExecute();
   pDialog = new ProgressDialog(Act_dosen.this);
   pDialog.setMessage("Loading Data ...");
   pDialog.setIndeterminate(false);
   pDialog.setCancelable(false);
   pDialog.show();
  }

  @Override
  protected String doInBackground(String... arg0) {
   
  String url = "http://siaka.akparmakassar.com/jsonx/dosen.php";


  JSONParser jParser = new JSONParser();


   JSONObject json = jParser.ambilURL(url);
   
   try {
    contacts = json.getJSONArray("dosen");

    for (int i = 0; i < contacts.length(); i++) {
     JSONObject c = contacts.getJSONObject(i);
     HashMap<String, String> map = new HashMap<String, String>();

     
     String id = c.getString("nm_dosen").trim();
     String judul = c.getString("nmr_dosen").trim();
     
     
     map.put("nm_dosen", id);
     map.put("nmr_dosen", judul);
     
     contactList.add(map);
    }

   } catch (JSONException e) {
    
    
   }

   return null;
  }

  @Override
  protected void onPostExecute(String result) {
   // TODO Auto-generated method stub
   super.onPostExecute(result);   
   pDialog.dismiss();
   
   	ListAdapter adapter2 = new SimpleAdapter(getApplicationContext(),
     contactList, R.layout.tamp_dosen,
     new String[] { "nmr_dosen", "nm_dosen" }, new int[] { R.id.Nodos, R.id.namados});

   lv.setAdapter(adapter2);
  }

 }
    
    
    
}