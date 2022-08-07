package com.example.siaka_akpar_makassar;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.NetworkInfo.DetailedState;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Act_khs extends Activity {
		 ListView lv;
		 ProgressDialog pDialog;
		 JSONArray khs_aks = null;
		 JSONObject json = null;
		 String nim,url;
		 public String kode;
		 
		 
		 
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        
		        setContentView(R.layout.tamp_inf);
		        lv=(ListView)findViewById(R.id.list);
		        Act_set_get a = new Act_set_get();
		        nim = a.getnim();
		        url = "http://siaka.akparmakassar.com/jsonx/khs.php?nim="+nim;
		    	new AmbilData().execute();
		        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		   @Override
		   public void onItemClick(AdapterView<?> parent, View view, int position,
		     long id) { 
			   	String kode_ang = ((TextView) view.findViewById(R.id.kodeangk)).getText().toString();
			   	Intent in = new Intent(getApplicationContext(), Act_detail_khs.class);
				in.putExtra("url","http://siaka.akparmakassar.com/jsonx/dkhs.php");
				in.putExtra("kd_smtz", kode_ang);
				in.putExtra("nimz", nim);
				finish();
				startActivity(in);
			   	
		   }
		  });
}
		  

		  public class AmbilData extends AsyncTask<String, String, String> {
		   
			  ArrayList<HashMap<String, String>> angkatan = new ArrayList<HashMap<String, String>>();		  

			  @Override
			  protected void onPreExecute() {
				  super.onPreExecute();
				  pDialog = new ProgressDialog(Act_khs.this);
				  pDialog.setMessage("Loading Data ...");
				  pDialog.setIndeterminate(false);
				  pDialog.setCancelable(false);
				  pDialog.show();
			  }

			  @Override
			  protected String doInBackground(String... arg0) {
				  JSONParser jParser = new JSONParser();
				  json = jParser.ambilURL(url);
		   
				  try {
					  khs_aks = json.getJSONArray("khs");
					  for(int i = 0; i < khs_aks.length(); i++){
					  JSONObject ar = khs_aks.getJSONObject(i);
					  String smt = "Semester : "+ar.getString("smt");
					  HashMap<String, String> map = new HashMap<String, String>();
					  map.put("semester", smt);
					  map.put("kd_smt", ar.getString("smt"));
					  angkatan.add(map);
					  }
				  }
				  catch (JSONException e) {
					  e.printStackTrace();
				  }
				  return null;
			  }

			  @Override
			  protected void onPostExecute(String result) {
				  // TODO Auto-generated method stub
				  //json = null;
				  super.onPostExecute(result);
				  pDialog.dismiss();
				  ListAdapter adapter2 = new SimpleAdapter(getApplicationContext(), 
				  angkatan,R.layout.list_item,new String[] { "semester", "kd_smt"}, 
				   new int[] {R.id.tangk, R.id.kodeangk});
				  lv.setAdapter(adapter2);
				  //Toast.makeText(Act_khs.this, "Json: "+json.toString(), Toast.LENGTH_LONG).show();
		  }

}
		    
		    
		    
}