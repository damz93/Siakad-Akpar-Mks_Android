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
public class Act_matkul extends Activity {
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

  @Override
  protected void onPreExecute() {
   super.onPreExecute();
   pDialog = new ProgressDialog(Act_matkul.this);
   pDialog.setMessage("Loading Data ...");
   pDialog.setIndeterminate(false);
   pDialog.setCancelable(false);
   pDialog.show();
  }

  @Override
  protected String doInBackground(String... arg0) {
   
  String url = "http://siaka.akparmakassar.com/jsonx/mata_kuliah.php";


  JSONParser jParser = new JSONParser();


   JSONObject json = jParser.ambilURL(url);
   
   try {
    contacts = json.getJSONArray("matakuliah");

    for (int i = 0; i < contacts.length(); i++) {
     JSONObject c = contacts.getJSONObject(i);
     HashMap<String, String> map = new HashMap<String, String>();

     
     String kode = c.getString("kodemtk").trim();
     kode = "Kode: "+kode;
     String nama = c.getString("namamtk").trim();
     String sms = c.getString("semester").trim();
     sms = "Semester : "+sms;
     String sks = c.getString("sks").trim();
     sks = "Jumlah SKS : "+sks;
     
     map.put("kodemtk", kode);
     map.put("namamtk", nama);
     map.put("semester", sms);
     map.put("sks", sks);
     
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
     contactList, R.layout.tamp_matkul,
     new String[] { "kodemtk", "namamtk","semester","sks" }, new int[] { R.id.kode_matk, R.id.nama_matk, R.id.smester,R.id.jml_sks});

   lv.setAdapter(adapter2);
  }

 }
    
    
    
}
