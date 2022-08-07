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
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
@SuppressLint("ShowToast")
public class Act_informasi extends Activity {
 ListView lv;
 ProgressDialog pDialog;
 JSONArray contacts = null;
 public String kode;
 
 
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.tamp_inf);
        lv=(ListView)findViewById(R.id.list);       
        new AmbilData().execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

   @Override
   public void onItemClick(AdapterView<?> parent, View view, int position,
     long id) { 
	   String kode = ((TextView) view.findViewById(R.id.id)).getText().toString();

	   String judul = ((TextView) view.findViewById(R.id.judul))
			      .getText().toString();
	   String penulis = ((TextView) view.findViewById(R.id.penulis)).getText().toString();
	   String isiii = ((TextView) view.findViewById(R.id.intro)).getText().toString();
	   Intent in = new Intent(getApplicationContext(), Act_detail_informasi.class);
	   in.putExtra("id", kode);
	   in.putExtra("isi", isiii);
	   in.putExtra("judul", judul);
	   in.putExtra("penulis", penulis);
	   
	   startActivity(in);
   }
  });
  }


  public class AmbilData extends AsyncTask<String, String, String> {
   
  ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
  String id, judul, waktu, intro, penulis;

  @Override
  protected void onPreExecute() {
   super.onPreExecute();
   pDialog = new ProgressDialog(Act_informasi.this);
   pDialog.setMessage("Loading Data ...");
   pDialog.setIndeterminate(false);
   pDialog.setCancelable(false);
   pDialog.show();
  }

  @Override
  protected String doInBackground(String... arg0) {
  String url = "http://siaka.akparmakassar.com/jsonx/berita.php";


  JSONParser jParser = new JSONParser();


   JSONObject json = jParser.ambilURL(url);
   
   try {
    contacts = json.getJSONArray("artikel");

    for (int i = 0; i < contacts.length(); i++) {
     JSONObject c = contacts.getJSONObject(i);
     HashMap<String, String> map = new HashMap<String, String>();

     
     String id = c.getString("id").trim();
     String judul = c.getString("judul").trim();
     //String waktu = c.getString("waktu").trim();     
     String isi = Html.fromHtml((c.getString("isi").trim())).toString();
     //String isii = c.getString("i").substring(0,2)+"...(Baca Selengkapnya)";
     //isii = Html.fromHtml(isi).toString();
   
     String penulis = "Posted by "+c.getString("penulis").trim();
     penulis = penulis + " | "+c.getString("waktu");
    		 
    		 
     
     
     map.put("isi", isi);
     map.put("id", id);
     map.put("judul", judul);
     map.put("waktu", waktu);
     map.put("penulis", penulis);
     
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
     contactList, R.layout.tamp_info,
     new String[] {  "judul", "penulis", "isi","id" }, new int[] { R.id.judul,
       R.id.penulis, R.id.intro,R.id.id});

   lv.setAdapter(adapter2);
  }

 }
    
    
    
}